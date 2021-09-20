package io.github.kamilkapadia.karabast.rest.setup.controller;

import java.util.ArrayList;
import java.util.List;

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
import io.github.kamilkapadia.karabast.rest.RestResponse;
import io.github.kamilkapadia.karabast.rest.setup.response.JobResponse;
import io.github.kamilkapadia.karabast.rest.setup.response.RuleResponse;
import io.github.kamilkapadia.karabast.service.setup.JobService;
import io.github.kamilkapadia.karabast.util.RestUtil;

@RestController
@RequestMapping("/api")
public class JobRestContoller {
	
	@Autowired
	private JobService jobService;
	
	@GetMapping("/jobs/{jobId}")
	public ResponseEntity<RestResponse> findbyId(@PathVariable String jobId) {
		RestResponse response = new RestResponse(ServletUriComponentsBuilder.fromCurrentRequest());
		response.setMethod("GET");
		String baseUrlString = response.getRequestUrl().substring(0, response.getRequestUrl().indexOf("/api/"));

		try {
			long longId = Long.parseLong(jobId);

			Job job = jobService.findById(longId);

			if (job != null) {
				JobResponse data = new JobResponse(job, baseUrlString);
				response.setData(data);

				response.setStatus(200);
				response.setMessage("Success");
				response.setRecordCount(1);

				return new ResponseEntity<>(response, HttpStatus.OK);

			} else {
				return RestUtil.getNotFoundResponse(response);
			}
		} catch (Exception e) {
			return RestUtil.getInternalServerErrorResponse(response, e);
		}
	}
	
	@GetMapping("/jobs")
	public ResponseEntity<RestResponse> findAll() {
		RestResponse response = new RestResponse(ServletUriComponentsBuilder.fromCurrentRequest());
		response.setMethod("GET");
		String baseUrlString = response.getRequestUrl().substring(0, response.getRequestUrl().indexOf("/api/"));

		try {
			List<Job> jobs = jobService.findAll();

			List<JobResponse> data = new ArrayList<>();

			for (Job job : jobs) {
				data.add(new JobResponse(job, baseUrlString));
			}

			if (data != null && data.size() > 0) {
				response.setData(data);
				response.setStatus(200);
				response.setMessage("Success");
				response.setRecordCount(data.size());

				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				return RestUtil.getNotFoundResponse(response);
			}
		} catch (Exception e) {
			return RestUtil.getInternalServerErrorResponse(response, e);
		}
	}
	
	@GetMapping("/jobs/names/{name}")
	public ResponseEntity<RestResponse> findByName(@PathVariable String name) {
		RestResponse response = new RestResponse(ServletUriComponentsBuilder.fromCurrentRequest());
		response.setMethod("GET");
		String baseUrlString = response.getRequestUrl().substring(0, response.getRequestUrl().indexOf("/api/"));

		try {
			List<Job> jobs = jobService.findByName(name);

			List<JobResponse> data = new ArrayList<>();

			for (Job job : jobs) {
				data.add(new JobResponse(job, baseUrlString));
			}

			if (data != null && data.size() > 0) {
				response.setData(data);
				response.setStatus(200);
				response.setMessage("Success");
				response.setRecordCount(data.size());

				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				return RestUtil.getNotFoundResponse(response);
			}
		} catch (Exception e) {
			return RestUtil.getInternalServerErrorResponse(response, e);
		}
	}
}
