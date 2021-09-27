package io.github.kamilkapadia.karabast.rest.lookup.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.kamilkapadia.karabast.dto.lookup.StatusCode;
import io.github.kamilkapadia.karabast.service.lookup.StatusCodeService;

@RestController
@RequestMapping("/api")
public class StatusCodeRestController {

	@Autowired
	private StatusCodeService statusCodeService;
	
	@GetMapping("/statuscodes/{id}")
	public StatusCode find(@PathVariable int id) {
		return statusCodeService.findById(id);
	}
	
	@GetMapping("/statuscodes")
	public List<StatusCode> findAll() {
		return statusCodeService.findAll();
	}
}
