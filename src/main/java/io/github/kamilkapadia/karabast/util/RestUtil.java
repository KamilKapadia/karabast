package io.github.kamilkapadia.karabast.util;

import io.github.kamilkapadia.karabast.rest.RestResponse;

public class RestUtil {

	public static String getBaseUrl(String fullUrl) {
		return fullUrl.substring(0, fullUrl.indexOf("/api/"));
	}

	public static void setFields(RestResponse response, boolean success, int statusCode, String message, int count, Object data) {
		response.setSuccess(success);
		response.setStatusCode(statusCode);
		response.setMessage(message);
		response.setRecordCount(count);
		response.setData(data);
	}
}
