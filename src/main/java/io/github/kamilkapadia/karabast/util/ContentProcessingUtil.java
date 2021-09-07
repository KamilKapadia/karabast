package io.github.kamilkapadia.karabast.util;

import java.util.List;

import org.apache.commons.codec.binary.Base64;

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
				
				String content = JSONPathUtil.getString(document, contentPath.getContentPath());
				String mimeType = JSONPathUtil.getString(document, contentPath.getMimeTypePath());

				String filePath = contentPath.getDiskDir();
				String fileName = contentPath.getNamePath();
				
				byte[] data = Base64.decodeBase64(content);
				
				
				long crc = ChecksumUtil.getCRC32(data);
				long adler = ChecksumUtil.getAdler32(data);
				String md5 = ChecksumUtil.getMD5(data);
				String sha = ChecksumUtil.getSHA512(data);

				// TODO
				
			}
		}
		
	}

}
