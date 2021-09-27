package io.github.kamilkapadia.karabast.rest.lookup.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.kamilkapadia.karabast.dto.lookup.RuleCode;
import io.github.kamilkapadia.karabast.service.lookup.RuleCodeService;

@RestController
@RequestMapping("/api")
public class RuleCodeRestController {

	@Autowired
	private RuleCodeService ruleCodeService;
	
	@GetMapping("/rulecodes/{id}")
	public RuleCode find(@PathVariable int id) {
		return ruleCodeService.findById(id);
	}
	
	@GetMapping("/rulecodes")
	public List<RuleCode> findAll() {
		return ruleCodeService.findAll();
	}
}
