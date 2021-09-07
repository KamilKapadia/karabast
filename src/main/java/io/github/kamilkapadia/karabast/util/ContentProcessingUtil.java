package io.github.kamilkapadia.karabast.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.apache.commons.codec.binary.Base64;

import io.github.kamilkapadia.karabast.dto.data.Content;
import io.github.kamilkapadia.karabast.dto.data.ContentResult;
import io.github.kamilkapadia.karabast.dto.data.Result;
import io.github.kamilkapadia.karabast.dto.setup.ContentPath;
import io.github.kamilkapadia.karabast.dto.setup.Job;
import io.github.kamilkapadia.karabast.service.data.ContentResultService;
import io.github.kamilkapadia.karabast.service.data.ContentService;
import io.github.kamilkapadia.karabast.service.setup.ContentPathService;

public class ContentProcessingUtil {

	public static void persist(Job job, Result result, Object document, ContentPathService contentPathService,
			ContentService contentService, ContentResultService contentResultService) {
		
		List<ContentPath> contentPaths = contentPathService.findByJobId(job.getId());
		
		for (ContentPath contentPath : contentPaths) {
			if (contentPath.isActive()) {
				
				String contentString = JSONPathUtil.getString(document, contentPath.getContentPath());
				String mimeType = JSONPathUtil.getString(document, contentPath.getMimeTypePath());

				String filePath = contentPath.getDiskDir();
				String fileName = JSONPathUtil.getString(document, contentPath.getNamePath());
				
				byte[] data = Base64.decodeBase64(contentString);
				
				long contentLength = data.length;
				
				long crc = ChecksumUtil.getCRC32(data);
				long adler = ChecksumUtil.getAdler32(data);
				String md5 = ChecksumUtil.getMD5(data);
				String sha = ChecksumUtil.getSHA512(data);

				Content content = contentService.findbyNameAndChecksums(fileName, crc, adler, md5, sha);
				
				if (content == null) {
					content = new Content();
					
					content.setName(fileName);
					content.setMimeType(mimeType);
					content.setContentLength(contentLength);
					content.setCrc32(crc);
					content.setAdler32(adler);
					content.setMd5(md5);
					content.setSha512(sha);
					
					contentService.save(content);
					
					try {
						Files.write(Paths.get(filePath + "/" + fileName), data);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				ContentResult contentResult = new ContentResult();
				
				contentResult.setContent(content);
				contentResult.setResult(result);
				
				contentResultService.save(contentResult);
				
			}
		}
	}
}
