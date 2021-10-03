package io.github.kamilkapadia.karabast.rest.lookup.controller;

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
import io.github.kamilkapadia.karabast.rest.RestRequest;
import io.github.kamilkapadia.karabast.rest.RestResponse;
import io.github.kamilkapadia.karabast.rest.lookup.response.ActionCodeResponse;
import io.github.kamilkapadia.karabast.service.lookup.ActionCodeService;
import io.github.kamilkapadia.karabast.util.RestUtil;

@RestController
@RequestMapping("/api")
public class ActionCodeRestController {

	private static final Logger LOGGER = LogManager.getLogger(ActionCodeRestController.class);
	
	@Autowired
	private ActionCodeService actionCodeService;
	
	@GetMapping("/actioncodes/{id}")
	public ResponseEntity<RestResponse> find(@PathVariable String id) {
		RestRequest request = new RestRequest("GET", ServletUriComponentsBuilder.fromCurrentRequest());
		
		RestResponse response = new RestResponse(request);
		
		try {
			int intId = Integer.parseInt(id);
		
			ActionCode actionCode =  actionCodeService.findById(intId);
			
			if (actionCode != null) {
				ActionCodeResponse data = new ActionCodeResponse(actionCode);
				RestUtil.setFields(response, true, 200, "Find action code by id: completed", 1, data);
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				RestUtil.setFields(response, false, 404, "Find action code by id: record not found", 0, "");
				LOGGER.error("Not Found: requestId=" + response.getRequest().getId() + " " + response);
				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			}
			
		} catch (Exception e) {
			RestUtil.setFields(response, false, 500, "Find action code by id: error getting data", 0, "");
			LOGGER.error("Internal Server Error: requestId=" + response.getRequest().getId() + " " + response, e);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/actioncodes")
	public ResponseEntity<RestResponse> findAll() {
		RestRequest request = new RestRequest("GET", ServletUriComponentsBuilder.fromCurrentRequest());
		
		RestResponse response = new RestResponse(request);
		
		try {
			List<ActionCode> actionCodes =  actionCodeService.findAll();
			
			List<ActionCodeResponse> data = new ArrayList<>();
			
			for (ActionCode actionCode : actionCodes) {
				data.add(new ActionCodeResponse(actionCode));
			}
			
			if (data != null && data.size() > 0) {
				RestUtil.setFields(response, true, 200, "Find all actions codes: completed", data.size(), data);
				LOGGER.info("Request Completed: requestId=" + response.getRequest().getId() + " " + response);
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				RestUtil.setFields(response, false, 404, "Find all action codes: records not found", 0, "");
				LOGGER.error("Not Found: requestId=" + response.getRequest().getId() + " " + response);
				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			}
			
		} catch (Exception e) {
			RestUtil.setFields(response, false, 500, "Find all action codes: error getting data", 0, "");
			LOGGER.error("Internal Server Error: requestId=" + response.getRequest().getId() + " " + response, e);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
