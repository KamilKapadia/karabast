package io.github.kamilkapadia.karabast.rest.setup.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.kamilkapadia.karabast.dto.setup.Rule;
import io.github.kamilkapadia.karabast.rest.setup.model.RuleBean;
import io.github.kamilkapadia.karabast.service.setup.RuleService;

@RestController
@RequestMapping("/api")
public class RuleRestController {
	
	@Autowired
	private RuleService ruleService;
	
	@GetMapping("/rules/{id}")
	public RuleBean find(@PathVariable long id) {
		Rule rule = ruleService.findById(id);
		RuleBean bean = new RuleBean(rule);
		return bean;
	}
	
	@GetMapping("/rules")
	public List<RuleBean> findAll() {
		List<Rule> rules =  ruleService.findAll();
		
		List<RuleBean> beans = new ArrayList<>();
		
		for (Rule rule : rules) {
			beans.add(new RuleBean(rule));
		}
		
		return beans;
	}
	
	@GetMapping("/rules/jobs/{id}")
	public List<RuleBean> findByJobId(@PathVariable long id) {
		List<Rule> rules = ruleService.findByJobId(id);
		
		List<RuleBean> beans = new ArrayList<>();
		
		for (Rule rule : rules) {
			beans.add(new RuleBean(rule));
		}
		
		return beans;
	}
}
