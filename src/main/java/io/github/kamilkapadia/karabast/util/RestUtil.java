package io.github.kamilkapadia.karabast.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.github.kamilkapadia.karabast.rest.RestResponse;

public class RestUtil {

	private static final Logger LOGGER = LogManager.getLogger(RestUtil.class);
	
	public static ResponseEntity<RestResponse> getNotFoundResponse(RestResponse response) {
		response.setStatus(404);
		response.setMessage("Not Found");
		
		LOGGER.error("Not Found - requestId=" + response.getRequestId() + " " + response);
		
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

	public static ResponseEntity<RestResponse> getInternalServerErrorResponse(RestResponse response, Exception e) {
		response.setStatus(500);
		response.setMessage("Internal Server Error - " + e.getClass().getName() + " : " + e.getMessage());

		LOGGER.error("Internal Server Error - requestId=" + response.getRequestId() + " " + response, e);

		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
