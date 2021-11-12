package io.github.kamilkapadia.karabast.components.data.result;

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
public class ResultRestContoller {

	private static final Logger LOGGER = LogManager.getLogger(ResultRestContoller.class);
	
	@Autowired
	private ResultService resultService;
	
	@GetMapping("/results/{id}")
	public ResponseEntity<RestResponse> find(@PathVariable long id) {
		RestRequest request = new RestRequest("GET", ServletUriComponentsBuilder.fromCurrentRequest());
		String baseUrlString = RestUtil.getBaseUrl(request.getUrl());
		
		RestResponse response = new RestResponse(request);
		
		try {
			Result result = resultService.findById(id);
			
			if (result != null) {
				ResultModel data = new ResultModel(result, baseUrlString);
				RestUtil.setFields(response, true, 200, "Find result by id: completed", 1, data);
				LOGGER.info("Request Completed: requestId=" + response.getRequest().getId() + " " + response);
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				RestUtil.setFields(response, false, 404, "Find result by id: record not found", 0, "");
				LOGGER.error("Not Found: requestId=" + response.getRequest().getId() + " " + response);
				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			RestUtil.setFields(response, false, 500, "Find result by id: error getting data", 0, "");
			LOGGER.error("Internal Server Error: requestId=" + response.getRequest().getId() + " " + response, e);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/results")
	public ResponseEntity<RestResponse> findAll() {
		RestRequest request = new RestRequest("GET", ServletUriComponentsBuilder.fromCurrentRequest());
		String baseUrlString = RestUtil.getBaseUrl(request.getUrl());

		RestResponse response = new RestResponse(request);
		
		try {
			List<Result> results = resultService.findAll();

			List<ResultModel> data = new ArrayList<>();

			for (Result result : results) {
				data.add(new ResultModel(result, baseUrlString));
			}

			if (data != null && data.size() > 0) {
				RestUtil.setFields(response, true, 200, "Find all results: completed", data.size(), data);
				LOGGER.info("Request Completed: requestId=" + response.getRequest().getId() + " " + response);
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				RestUtil.setFields(response, false, 404, "Find all results: no records found", 0, "");
				LOGGER.error("Not Found: requestId=" + response.getRequest().getId() + " " + response);
				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			RestUtil.setFields(response, false, 500, "Find all results: error getting data", 0, "");
			LOGGER.error("Internal Server Error: requestId=" + response.getRequest().getId() + " " + response, e);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/results/jobs/{id}")
	public ResponseEntity<RestResponse> findByJobId(@PathVariable long id) {
		RestRequest request = new RestRequest("GET", ServletUriComponentsBuilder.fromCurrentRequest());
		String baseUrlString = RestUtil.getBaseUrl(request.getUrl());
		
		RestResponse response = new RestResponse(request);
		
		try {
			List<Result> results = resultService.findByJobId(id);
			
			List<ResultModel> data = new ArrayList<>();
			
			for (Result result : results) {
				data.add(new ResultModel(result, baseUrlString));
			}

			if (data != null && data.size() > 0) {
				RestUtil.setFields(response, true, 200, "Find results by job id: completed", data.size(), data);
				LOGGER.info("Request Completed: requestId=" + response.getRequest().getId() + " " + response);
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				RestUtil.setFields(response, false, 404, "Find results by job id: no records found", 0, "");
				LOGGER.error("Not Found: requestId=" + response.getRequest().getId() + " " + response);
				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			RestUtil.setFields(response, false, 500, "Find results by job id: error getting data", 0, "");
			LOGGER.error("Internal Server Error: requestId=" + response.getRequest().getId() + " " + response, e);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/results/jobs/{jobId}/runs/{run}")
	public ResponseEntity<RestResponse> findByJobIdAndRunId(@PathVariable long jobId, @PathVariable long run) {
		RestRequest request = new RestRequest("GET", ServletUriComponentsBuilder.fromCurrentRequest());
		String baseUrlString = RestUtil.getBaseUrl(request.getUrl());
		
		RestResponse response = new RestResponse(request);
		
		try {
			Result result = resultService.findByJobIdAndRunId(jobId, run);
			
			if (result != null) {
				ResultModel data = new ResultModel(result, baseUrlString);
				RestUtil.setFields(response, true, 200, "Find result by job id and run id: completed", 1, data);
				LOGGER.info("Request Completed: requestId=" + response.getRequest().getId() + " " + response);
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				RestUtil.setFields(response, false, 404, "Find result by job id and run id: record not found", 0, "");
				LOGGER.error("Not Found: requestId=" + response.getRequest().getId() + " " + response);
				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			RestUtil.setFields(response, false, 500, "Find result by job id and run id: error getting data", 0, "");
			LOGGER.error("Internal Server Error: requestId=" + response.getRequest().getId() + " " + response, e);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

