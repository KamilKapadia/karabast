package io.github.kamilkapadia.karabast.rest.setup.controller;

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

import io.github.kamilkapadia.karabast.dto.setup.Job;
import io.github.kamilkapadia.karabast.dto.setup.Rule;
import io.github.kamilkapadia.karabast.rest.RestRequest;
import io.github.kamilkapadia.karabast.rest.RestResponse;
import io.github.kamilkapadia.karabast.rest.setup.response.JobResponse;
import io.github.kamilkapadia.karabast.rest.setup.response.RuleResponse;
import io.github.kamilkapadia.karabast.service.setup.JobService;
import io.github.kamilkapadia.karabast.util.RestUtil;

@RestController
@RequestMapping("/api")
public class JobRestContoller {
	
	private static final Logger LOGGER = LogManager.getLogger(JobRestContoller.class);

	
	@Autowired
	private JobService jobService;
	
	@GetMapping("/jobs/{jobId}")
	public ResponseEntity<RestResponse> findbyId(@PathVariable String jobId) {
		RestRequest request = new RestRequest("GET", ServletUriComponentsBuilder.fromCurrentRequest());
		String baseUrlString = RestUtil.getBaseUrl(request.getUrl());

		RestResponse response = new RestResponse(request);
		
		try {
			long longId = Long.parseLong(jobId);

			Job job = jobService.findById(longId);

			if (job != null) {
				JobResponse data = new JobResponse(job, baseUrlString);
				RestUtil.setFields(response, true, 200, "Find job by id: completed", 1, data);
				LOGGER.info("Request Completed: requestId=" + response.getRequest().getId() + " " + response);
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				RestUtil.setFields(response, false, 404, "Find job by id: record not found", 0, "");
				LOGGER.error("Not Found: requestId=" + response.getRequest().getId() + " " + response);
				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			RestUtil.setFields(response, false, 500, "Find job by id: error getting data", 0, "");
			LOGGER.error("Internal Server Error: requestId=" + response.getRequest().getId() + " " + response, e);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/jobs")
	public ResponseEntity<RestResponse> findAll() {
		RestRequest request = new RestRequest("GET", ServletUriComponentsBuilder.fromCurrentRequest());
		String baseUrlString = RestUtil.getBaseUrl(request.getUrl());

		RestResponse response = new RestResponse(request);
		
		try {
			List<Job> jobs = jobService.findAll();

			List<JobResponse> data = new ArrayList<>();

			for (Job job : jobs) {
				data.add(new JobResponse(job, baseUrlString));
			}

			if (data != null && data.size() > 0) {
				RestUtil.setFields(response, true, 200, "Find all jobs: completed", data.size(), data);
				LOGGER.info("Request Completed: requestId=" + response.getRequest().getId() + " " + response);
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				RestUtil.setFields(response, false, 404, "Find all jobs: no records found", 0, "");
				LOGGER.error("Not Found: requestId=" + response.getRequest().getId() + " " + response);
				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			RestUtil.setFields(response, false, 500, "Find all jobs: error getting data", 0, "");
			LOGGER.error("Internal Server Error: requestId=" + response.getRequest().getId() + " " + response, e);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/jobs/names/{name}")
	public ResponseEntity<RestResponse> findByName(@PathVariable String name) {
		RestRequest request = new RestRequest("GET", ServletUriComponentsBuilder.fromCurrentRequest());
		String baseUrlString = RestUtil.getBaseUrl(request.getUrl());

		RestResponse response = new RestResponse(request);
		
		try {
			List<Job> jobs = jobService.findByName(name);

			List<JobResponse> data = new ArrayList<>();

			for (Job job : jobs) {
				data.add(new JobResponse(job, baseUrlString));
			}

			if (data != null && data.size() > 0) {
				RestUtil.setFields(response, true, 200, "Find job by name: completed", data.size(), data);
				LOGGER.info("Request Completed: requestId=" + response.getRequest().getId() + " " + response);
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				RestUtil.setFields(response, false, 404, "Find job by name: record not found", 0, "");
				LOGGER.error("Not Found: requestId=" + response.getRequest().getId() + " " + response);
				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			RestUtil.setFields(response, false, 500, "Find job by name: error getting data", 0, "");
			LOGGER.error("Internal Server Error: requestId=" + response.getRequest().getId() + " " + response, e);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
