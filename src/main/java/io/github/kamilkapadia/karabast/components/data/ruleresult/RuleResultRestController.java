package io.github.kamilkapadia.karabast.components.data.ruleresult;

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
public class RuleResultRestController {
	
	private static final Logger LOGGER = LogManager.getLogger(RuleResultRestController.class);
	
	@Autowired
	private RuleResultService ruleResultService;
	
	@GetMapping("/ruleResults/{id}")
	public ResponseEntity<RestResponse> find(@PathVariable long id) {
		RestRequest request = new RestRequest("GET", ServletUriComponentsBuilder.fromCurrentRequest());
		String baseUrlString = RestUtil.getBaseUrl(request.getUrl());
		
		RestResponse response = new RestResponse(request);
		
		try {
			RuleResult ruleResult = ruleResultService.findById(id);
			
			if (ruleResult != null) {
				RuleResultModel data = new RuleResultModel(ruleResult, baseUrlString);
				RestUtil.setFields(response, true, 200, "Find rule result by id: completed", 1, data);
				LOGGER.info("Request Completed: requestId=" + response.getRequest().getId() + " " + response);
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				RestUtil.setFields(response, false, 404, "Find rule result by id: record not found", 0, "");
				LOGGER.error("Not Found: requestId=" + response.getRequest().getId() + " " + response);
				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			RestUtil.setFields(response, false, 500, "Find rule result by id: error getting data", 0, "");
			LOGGER.error("Internal Server Error: requestId=" + response.getRequest().getId() + " " + response, e);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/ruleResults")
	public ResponseEntity<RestResponse> findAll() {
		RestRequest request = new RestRequest("GET", ServletUriComponentsBuilder.fromCurrentRequest());
		String baseUrlString = RestUtil.getBaseUrl(request.getUrl());
		
		RestResponse response = new RestResponse(request);
		
		try {
			List<RuleResult> ruleResults = ruleResultService.findAll();
			
			List<RuleResultModel> data = new ArrayList<>();
			
			for (RuleResult ruleResult : ruleResults) {
				data.add(new RuleResultModel(ruleResult, baseUrlString));
			}
			
			if (data != null && data.size() > 0) {
				RestUtil.setFields(response, true, 200, "Find all rule results: completed", data.size(), data);
				LOGGER.info("Request Completed: requestId=" + response.getRequest().getId() + " " + response);
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				RestUtil.setFields(response, false, 404, "Find all rule results: no records found", 0, "");
				LOGGER.error("Not Found: requestId=" + response.getRequest().getId() + " " + response);
				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			}
			
		} catch (Exception e) {
			RestUtil.setFields(response, false, 500, "Find all rule results: error getting data", 0, "");
			LOGGER.error("Internal Server Error: requestId=" + response.getRequest().getId() + " " + response, e);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/ruleResults/results/{id}")
	public ResponseEntity<RestResponse> findByResultId(@PathVariable long id) {
		RestRequest request = new RestRequest("GET", ServletUriComponentsBuilder.fromCurrentRequest());
		String baseUrlString = RestUtil.getBaseUrl(request.getUrl());
		
		RestResponse response = new RestResponse(request);
		
		try {
			List<RuleResult> ruleResults = ruleResultService.findByResultId(id);
			
			List<RuleResultModel> data = new ArrayList<>();
			
			for (RuleResult ruleResult : ruleResults) {
				data.add(new RuleResultModel(ruleResult, baseUrlString));
			}
			
			if (data != null && data.size() > 0) {
				RestUtil.setFields(response, true, 200, "Find rule results by result id: completed", data.size(), data);
				LOGGER.info("Request Completed: requestId=" + response.getRequest().getId() + " " + response);
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				RestUtil.setFields(response, false, 404, "Find rule results by result id: no records found", 0, "");
				LOGGER.error("Not Found: requestId=" + response.getRequest().getId() + " " + response);
				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			}
			
		} catch (Exception e) {
			RestUtil.setFields(response, false, 500, "Find rule results by result id: error getting data", 0, "");
			LOGGER.error("Internal Server Error: requestId=" + response.getRequest().getId() + " " + response, e);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

