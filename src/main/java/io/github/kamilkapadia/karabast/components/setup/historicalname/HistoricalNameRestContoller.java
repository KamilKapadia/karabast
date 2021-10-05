package io.github.kamilkapadia.karabast.components.setup.historicalname;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.github.kamilkapadia.karabast.components.RestRequest;
import io.github.kamilkapadia.karabast.components.RestResponse;
import io.github.kamilkapadia.karabast.util.RestUtil;

@RestController
@RequestMapping("/api")
public class HistoricalNameRestContoller {
	
	private static final Logger LOGGER = LogManager.getLogger(HistoricalNameRestContoller.class);
	
	@Autowired
	private HistoricalNameService historicalNameService;
	
	@GetMapping("/historicalnames/{id}")
	public ResponseEntity<RestResponse> find(@PathVariable String id) {
		RestRequest request = new RestRequest("GET", ServletUriComponentsBuilder.fromCurrentRequest());
		String baseUrlString = RestUtil.getBaseUrl(request.getUrl());

		RestResponse response = new RestResponse(request);

		try {
			long longId = Long.parseLong(id);

			HistoricalName historicalName = historicalNameService.findById(longId);

			if (historicalName != null) {
				HistoricalNameModel data = new HistoricalNameModel(historicalName, baseUrlString);
				RestUtil.setFields(response, true, 200, "Find historcal name by id: completed", 1, data);
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				RestUtil.setFields(response, false, 404, "Find historcal name by id: record not found", 0, "");
				LOGGER.error("Not Found: requestId=" + response.getRequest().getId() + " " + response);
				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			RestUtil.setFields(response, false, 500, "Find historcal name by id: error getting data", 0, "");
			LOGGER.error("Internal Server Error: requestId=" + response.getRequest().getId() + " " + response, e);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/historicalnames")
	public ResponseEntity<RestResponse> findAll() {
		RestRequest request = new RestRequest("GET", ServletUriComponentsBuilder.fromCurrentRequest());
		String baseUrlString = RestUtil.getBaseUrl(request.getUrl());

		RestResponse response = new RestResponse(request);
		
		try {
			List<HistoricalName> historicalNames = historicalNameService.findAll();

			List<HistoricalNameModel> data = new ArrayList<>();

			for (HistoricalName historicalName : historicalNames) {
				data.add(new HistoricalNameModel(historicalName, baseUrlString));
			}

			if (data != null && data.size() > 0) {
				RestUtil.setFields(response, true, 200, "Find all historical names: completed", data.size(), data);
				LOGGER.info("Request Completed: requestId=" + response.getRequest().getId() + " " + response);
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				RestUtil.setFields(response, false, 404, "Find all historical names: no records found", 0, "");
				LOGGER.error("Not Found: requestId=" + response.getRequest().getId() + " " + response);
				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			RestUtil.setFields(response, false, 500, "Find all historical names: error getting data", 0, "");
			LOGGER.error("Internal Server Error: requestId=" + response.getRequest().getId() + " " + response, e);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/historicalnames/jobs/{id}")
	public ResponseEntity<RestResponse> findByJobId(@PathVariable String id) {
		RestRequest request = new RestRequest("GET", ServletUriComponentsBuilder.fromCurrentRequest());
		String baseUrlString = RestUtil.getBaseUrl(request.getUrl());

		RestResponse response = new RestResponse(request);
		
		try {
			long longId = Long.parseLong(id);

			List<HistoricalName> historicalNames = historicalNameService.findByJobId(longId);

			List<HistoricalNameModel> data = new ArrayList<>();

			for (HistoricalName historicalName : historicalNames) {
				data.add(new HistoricalNameModel(historicalName, baseUrlString));
			}

			if (data != null && data.size() > 0) {
				RestUtil.setFields(response, true, 200, "Find historical names by job id: completed", data.size(), data);
				LOGGER.info("Request Completed: requestId=" + response.getRequest().getId() + " " + response);
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				RestUtil.setFields(response, false, 404, "Find historical names by job id: record not found", 0, "");
				LOGGER.error("Not Found: requestId=" + response.getRequest().getId() + " " + response);
				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			RestUtil.setFields(response, false, 500, "Find historical names by job id: error getting data", 0, "");
			LOGGER.error("Internal Server Error: requestId=" + response.getRequest().getId() + " " + response, e);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

