package io.github.kamilkapadia.karabast.components.lookup.rulecode;

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
public class RuleCodeRestController {

	private static final Logger LOGGER = LogManager.getLogger(RuleCodeRestController.class);
	
	@Autowired
	private RuleCodeService ruleCodeService;
	
	@GetMapping("/rulecodes/{id}")
	public ResponseEntity<RestResponse> find(@PathVariable String id) {
		RestRequest request = new RestRequest("GET", ServletUriComponentsBuilder.fromCurrentRequest());
		
		RestResponse response = new RestResponse(request);
		
		try {
			int intId = Integer.parseInt(id);
		
			RuleCode ruleCode =  ruleCodeService.findById(intId);
			
			if (ruleCode != null) {
				RuleCodeModel data = new RuleCodeModel(ruleCode);
				RestUtil.setFields(response, true, 200, "Find rule code by id: completed", 1, data);
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				RestUtil.setFields(response, false, 404, "Find rule code by id: record not found", 0, "");
				LOGGER.error("Not Found: requestId=" + response.getRequest().getId() + " " + response);
				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			}
			
		} catch (Exception e) {
			RestUtil.setFields(response, false, 500, "Find rule code by id: error getting data", 0, "");
			LOGGER.error("Internal Server Error: requestId=" + response.getRequest().getId() + " " + response, e);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/rulecodes")
	public ResponseEntity<RestResponse> findAll() {
		RestRequest request = new RestRequest("GET", ServletUriComponentsBuilder.fromCurrentRequest());
		
		RestResponse response = new RestResponse(request);
		
		try {
			List<RuleCode> ruleCodes =  ruleCodeService.findAll();

			List<RuleCodeModel> data = new ArrayList<>();
			
			for (RuleCode ruleCode : ruleCodes) {
				data.add(new RuleCodeModel(ruleCode));
			}
			
			if (data != null && data.size() > 0) {
				RestUtil.setFields(response, true, 200, "Find all rule codes: completed", data.size(), data);
				LOGGER.info("Request Completed: requestId=" + response.getRequest().getId() + " " + response);
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				RestUtil.setFields(response, false, 404, "Find all rule codes: records not found", 0, "");
				LOGGER.error("Not Found: requestId=" + response.getRequest().getId() + " " + response);
				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			}
			
		} catch (Exception e) {
			RestUtil.setFields(response, false, 500, "Find all rule codes: error getting data", 0, "");
			LOGGER.error("Internal Server Error: requestId=" + response.getRequest().getId() + " " + response, e);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
