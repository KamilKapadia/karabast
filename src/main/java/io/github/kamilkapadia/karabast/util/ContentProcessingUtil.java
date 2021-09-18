package io.github.kamilkapadia.karabast.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;

import io.github.kamilkapadia.karabast.dto.data.Content;
import io.github.kamilkapadia.karabast.dto.data.ContentResult;
import io.github.kamilkapadia.karabast.dto.data.Result;
import io.github.kamilkapadia.karabast.dto.setup.ContentPath;
import io.github.kamilkapadia.karabast.dto.setup.Job;
import io.github.kamilkapadia.karabast.service.data.ContentResultService;
import io.github.kamilkapadia.karabast.service.data.ContentService;
import io.github.kamilkapadia.karabast.service.setup.ContentPathService;
import net.minidev.json.JSONArray;

public class ContentProcessingUtil {

	private static final String CONTENT_ARRAY_PATH = "$.data.contents";
	private static final String CONTENT_NAME_PATH = "$.data.contents[%d].name";
	private static final String CONTENT_CONTENT_PATH = "$.data.contents[%d].content";
	private static final String CONTENT_MIMETYPE_PATH = "$.data.contents[%d].mimeType";

	public static void persist(Job job, Result result, Object document, ContentPathService contentPathService,
			ContentService contentService, ContentResultService contentResultService) {

		List<ContentPath> contentPaths = contentPathService.findByJobId(job.getId());

		Map<String, String> namePaths = new HashMap<>();

		for (ContentPath contentPath : contentPaths) {

			if (contentPath.isActive()) {
				String fileName = contentPath.getName();
				String filePath = contentPath.getDiskDir();

				namePaths.put(fileName, filePath);
			}
		}

		JSONArray contentArray = JSONPathUtil.getJSONArray(document, CONTENT_ARRAY_PATH);

		if (contentArray != null) {
			for (int i = 0; i < contentArray.size(); i++) {

				String fileName = JSONPathUtil.getString(document, String.format(CONTENT_NAME_PATH, i));
				String contentString = JSONPathUtil.getString(document, String.format(CONTENT_CONTENT_PATH, i));
				String mimeType = JSONPathUtil.getString(document, String.format(CONTENT_MIMETYPE_PATH, i)); 
				String filePath = namePaths.get(fileName);

				if (filePath != null) {
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
					}

					ContentResult contentResult = new ContentResult();

					contentResult.setContent(content);
					contentResult.setResult(result);

					contentResultService.save(contentResult);
					
					File theFile = new File(filePath + "/" + fileName);
					
					if (!theFile.exists()) {
						try {
							Files.write(Paths.get(filePath + "/" + fileName), data);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		}
	}
}
