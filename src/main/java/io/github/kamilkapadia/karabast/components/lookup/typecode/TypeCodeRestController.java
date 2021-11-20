package io.github.kamilkapadia.karabast.components.lookup.typecode;

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
public class TypeCodeRestController {

	private static final Logger LOGGER = LogManager.getLogger(TypeCodeRestController.class);
	
	@Autowired
	private TypeCodeService typeCodeService;
	
	@GetMapping("/typecodes/{id}")
	public ResponseEntity<RestResponse> find(@PathVariable String id) {
		RestRequest request = new RestRequest("GET", ServletUriComponentsBuilder.fromCurrentRequest());
		
		RestResponse response = new RestResponse(request);
		
		try {
			int intId = Integer.parseInt(id);
		
			TypeCode typeCode =  typeCodeService.findById(intId);
			
			if (typeCode != null) {
				TypeCodeModel data = new TypeCodeModel(typeCode);
				RestUtil.setFields(response, true, 200, "Find type code by id: completed", 1, data);
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				RestUtil.setFields(response, false, 404, "Find type code by id: record not found", 0, "");
				LOGGER.error("Not Found: requestId=" + response.getRequest().getId() + " " + response);
				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			}
			
		} catch (Exception e) {
			RestUtil.setFields(response, false, 500, "Find type code by id: error getting data", 0, "");
			LOGGER.error("Internal Server Error: requestId=" + response.getRequest().getId() + " " + response, e);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/typecodes")
	public ResponseEntity<RestResponse> findAll() {
		RestRequest request = new RestRequest("GET", ServletUriComponentsBuilder.fromCurrentRequest());
		
		RestResponse response = new RestResponse(request);
		
		try {
			List<TypeCode> typeCodes =  typeCodeService.findAll();
			
			List<TypeCodeModel> data = new ArrayList<>();
			
			for (TypeCode typeCode : typeCodes) {
				data.add(new TypeCodeModel(typeCode));
			}
			
			if (data != null && data.size() > 0) {
				RestUtil.setFields(response, true, 200, "Find all type codes: completed", data.size(), data);
				LOGGER.info("Request Completed: requestId=" + response.getRequest().getId() + " " + response);
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				RestUtil.setFields(response, false, 404, "Find all type codes: records not found", 0, "");
				LOGGER.error("Not Found: requestId=" + response.getRequest().getId() + " " + response);
				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			}
			
		} catch (Exception e) {
			RestUtil.setFields(response, false, 500, "Find all type codes: error getting data", 0, "");
			LOGGER.error("Internal Server Error: requestId=" + response.getRequest().getId() + " " + response, e);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}