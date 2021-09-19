package io.github.kamilkapadia.karabast.rest.setup.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

@RestController
@RequestMapping("/api")
public class RuleRestController {

	@Autowired
	private RuleService ruleService;

	@GetMapping("/rules/{id}")
	public ResponseEntity<RestResponse> find(@PathVariable long id) {
		RestResponse response = new RestResponse(ServletUriComponentsBuilder.fromCurrentRequest());
		response.setMethod("GET");
		String baseUrlString = response.getRequestUrl().substring(0, response.getRequestUrl().indexOf("/api/")); 

		try {
			Rule rule = ruleService.findById(id);

			if (rule != null) {
				RuleResponse data = new RuleResponse(rule, baseUrlString);
				response.setData(data);

				response.setStatus(200);
				response.setMessage("Success");
				response.setRecordCount(1);

				return new ResponseEntity<>(response, HttpStatus.OK);

			} else {
				response.setStatus(404);
				response.setMessage("Not Found");

				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			response.setStatus(500);
			response.setMessage("Internal Server Error : " + e.getMessage());

			// TODO - log the error with the requestId

			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/rules")
	public ResponseEntity<RestResponse> findAll() {
		RestResponse response = new RestResponse(ServletUriComponentsBuilder.fromCurrentRequest());
		response.setMethod("GET");
		String baseUrlString = response.getRequestUrl().substring(0, response.getRequestUrl().indexOf("/api/"));

		try {
			List<Rule> rules =  ruleService.findAll();

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
				response.setStatus(404);
				response.setMessage("Not Found");

				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			response.setStatus(500);
			response.setMessage("Internal Server Error : " + e.getMessage());

			// TODO - log the error with the requestId

			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/rules/jobs/{id}")
	public  ResponseEntity<RestResponse> findByJobId(@PathVariable long id) {
		RestResponse response = new RestResponse(ServletUriComponentsBuilder.fromCurrentRequest());
		response.setMethod("GET");
		String baseUrlString = response.getRequestUrl().substring(0, response.getRequestUrl().indexOf("/api/"));

		try { 
			List<Rule> rules = ruleService.findByJobId(id);

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
				response.setStatus(404);
				response.setMessage("Not Found");

				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			response.setStatus(500);
			response.setMessage("Internal Server Error : " + e.getMessage());

			// TODO - log the error with the requestId

			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}	}
}
