package io.github.kamilkapadia.karabast.rest.setup;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.kamilkapadia.karabast.dto.setup.Rule;
import io.github.kamilkapadia.karabast.service.setup.RuleService;

@RestController
@RequestMapping("/api")
public class RuleRestController {
	
	@Autowired
	private RuleService ruleService;
	
	@GetMapping("/rules/{id}")
	public Rule find(@PathVariable long id) {
		return ruleService.findById(id);
	}
	
	@GetMapping("/rules")
	public List<Rule> findAll() {
		return ruleService.findAll();
	}
	
	@GetMapping("/rules/jobs/{id}")
	public List<Rule> findByJobId(@PathVariable long id) {
		return ruleService.findByJobId(id);
	}
}
