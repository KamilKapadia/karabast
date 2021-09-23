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

import io.github.kamilkapadia.karabast.dto.setup.Rule;
import io.github.kamilkapadia.karabast.rest.RestResponse;
import io.github.kamilkapadia.karabast.rest.setup.response.RuleResponse;
import io.github.kamilkapadia.karabast.service.setup.RuleService;
import io.github.kamilkapadia.karabast.util.RestUtil;

@RestController
@RequestMapping("/api")
public class RuleRestController {

	private static final Logger LOGGER = LogManager.getLogger(RuleRestController.class);
	
	@Autowired
	private RuleService ruleService;

	@GetMapping("/rules/{id}")
	public ResponseEntity<RestResponse> find(@PathVariable String id) {
		RestResponse response = new RestResponse(ServletUriComponentsBuilder.fromCurrentRequest());
		response.setMethod("GET");
		String baseUrlString = response.getRequestUrl().substring(0, response.getRequestUrl().indexOf("/api/"));

		LOGGER.info("RuleRestController find - requestId=" + response.getRequestId());
		
		try {
			long longId = Long.parseLong(id);

			Rule rule = ruleService.findById(longId);

			if (rule != null) {
				RuleResponse data = new RuleResponse(rule, baseUrlString);
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

	@GetMapping("/rules")
	public ResponseEntity<RestResponse> findAll() {
		RestResponse response = new RestResponse(ServletUriComponentsBuilder.fromCurrentRequest());
		response.setMethod("GET");
		String baseUrlString = response.getRequestUrl().substring(0, response.getRequestUrl().indexOf("/api/"));

		LOGGER.info("RuleRestController findAll - requestId=" + response.getRequestId());
		
		try {
			List<Rule> rules = ruleService.findAll();

			List<RuleResponse> data = new ArrayList<>();

			for (Rule rule : rules) {
				data.add(new RuleResponse(rule, baseUrlString));
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

	@GetMapping("/rules/jobs/{id}")
	public ResponseEntity<RestResponse> findByJobId(@PathVariable String id) {
		RestResponse response = new RestResponse(ServletUriComponentsBuilder.fromCurrentRequest());
		response.setMethod("GET");
		String baseUrlString = response.getRequestUrl().substring(0, response.getRequestUrl().indexOf("/api/"));

		LOGGER.info("RuleRestController findByJobId - requestId=" + response.getRequestId());
		
		try {
			long longId = Long.parseLong(id);

			List<Rule> rules = ruleService.findByJobId(longId);

			List<RuleResponse> data = new ArrayList<>();

			for (Rule rule : rules) {
				data.add(new RuleResponse(rule, baseUrlString));
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
