package io.github.kamilkapadia.karabast.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import io.github.kamilkapadia.karabast.rest.RestResponse;

public class RestUtil {

	public static ResponseEntity<RestResponse> getNotFoundResponse(RestResponse response) {
		response.setStatus(404);
		response.setMessage("Not Found");
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

	public static ResponseEntity<RestResponse> getInternalServerErrorResponse(RestResponse response, Exception e) {
		response.setStatus(500);
		response.setMessage("Internal Server Error - " + e.getClass().getName() + " : " + e.getMessage());

		// TODO - log the error with the requestId

		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
