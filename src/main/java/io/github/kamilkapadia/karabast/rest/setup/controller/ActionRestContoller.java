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

import io.github.kamilkapadia.karabast.dto.lookup.ActionCode;
import io.github.kamilkapadia.karabast.dto.lookup.StatusCode;
import io.github.kamilkapadia.karabast.dto.setup.Action;
import io.github.kamilkapadia.karabast.rest.RestRequest;
import io.github.kamilkapadia.karabast.rest.RestResponse;
import io.github.kamilkapadia.karabast.rest.setup.response.ActionResponse;
import io.github.kamilkapadia.karabast.service.lookup.ActionCodeService;
import io.github.kamilkapadia.karabast.service.lookup.StatusCodeService;
import io.github.kamilkapadia.karabast.service.setup.ActionService;
import io.github.kamilkapadia.karabast.util.RestUtil;

@RestController
@RequestMapping("/api")
public class ActionRestContoller {
	
	private static final Logger LOGGER = LogManager.getLogger(ActionRestContoller.class);
	
	@Autowired
	private ActionService actionService;
	
	@Autowired
	private ActionCodeService actionCodeService;
	
	@Autowired
	private StatusCodeService statusCodeService;
	
	@GetMapping("/actions/{id}")
	public ResponseEntity<RestResponse> find(@PathVariable String id) {
		RestRequest request = new RestRequest("GET", ServletUriComponentsBuilder.fromCurrentRequest());
		String baseUrlString = RestUtil.getBaseUrl(request.getUrl());

		RestResponse response = new RestResponse(request);

		try {
			long longId = Long.parseLong(id);

			Action action = actionService.findById(longId);
			
			if (action != null) {
				List<StatusCode> allStatusCodes = statusCodeService.findAll();
				List<ActionCode> allActionCodes = actionCodeService.findAll();
				
				ActionResponse data = new ActionResponse(action, allStatusCodes, allActionCodes, baseUrlString);
				RestUtil.setFields(response, true, 200, "Find action by id: completed", 1, data);
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				RestUtil.setFields(response, false, 404, "Find action by id: record not found", 0, "");
				LOGGER.error("Not Found: requestId=" + response.getRequest().getId() + " " + response);
				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			RestUtil.setFields(response, false, 500, "Find action by id: error getting data", 0, "");
			LOGGER.error("Internal Server Error: requestId=" + response.getRequest().getId() + " " + response, e);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/actions")
	public ResponseEntity<RestResponse> findAll() {
		RestRequest request = new RestRequest("GET", ServletUriComponentsBuilder.fromCurrentRequest());
		String baseUrlString = RestUtil.getBaseUrl(request.getUrl());

		RestResponse response = new RestResponse(request);
		
		try {
			List<Action> actions = actionService.findAll();

			List<ActionResponse> data = new ArrayList<>();

			List<StatusCode> allStatusCodes = statusCodeService.findAll();
			List<ActionCode> allActionCodes = actionCodeService.findAll();
			
			for (Action action : actions) {
				data.add(new ActionResponse(action, allStatusCodes, allActionCodes, baseUrlString));
			}

			if (data != null && data.size() > 0) {
				RestUtil.setFields(response, true, 200, "Find all actions: completed", data.size(), data);
				LOGGER.info("Request Completed: requestId=" + response.getRequest().getId() + " " + response);
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				RestUtil.setFields(response, false, 404, "Find all actions: no records found", 0, "");
				LOGGER.error("Not Found: requestId=" + response.getRequest().getId() + " " + response);
				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			RestUtil.setFields(response, false, 500, "Find all actions: error getting data", 0, "");
			LOGGER.error("Internal Server Error: requestId=" + response.getRequest().getId() + " " + response, e);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/actions/jobs/{id}")
	public ResponseEntity<RestResponse> findByJobId(@PathVariable String id) {
		RestRequest request = new RestRequest("GET", ServletUriComponentsBuilder.fromCurrentRequest());
		String baseUrlString = RestUtil.getBaseUrl(request.getUrl());

		RestResponse response = new RestResponse(request);

		try {
			long longId = Long.parseLong(id);

			List<Action> actions = actionService.findByJobId(longId);
			
			List<ActionResponse> data = new ArrayList<>();
			
			List<StatusCode> allStatusCodes = statusCodeService.findAll();
			List<ActionCode> allActionCodes = actionCodeService.findAll();
			
			for (Action action : actions) {
				data.add(new ActionResponse(action, allStatusCodes, allActionCodes, baseUrlString));
			}

			if (data != null && data.size() > 0) {
				RestUtil.setFields(response, true, 200, "Find actions by job id: completed", data.size(), data);
				LOGGER.info("Request Completed: requestId=" + response.getRequest().getId() + " " + response);
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				RestUtil.setFields(response, false, 404, "Find actions by job id: record not found", 0, "");
				LOGGER.error("Not Found: requestId=" + response.getRequest().getId() + " " + response);
				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			RestUtil.setFields(response, false, 500, "Find actions by job id: error getting data", 0, "");
			LOGGER.error("Internal Server Error: requestId=" + response.getRequest().getId() + " " + response, e);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
