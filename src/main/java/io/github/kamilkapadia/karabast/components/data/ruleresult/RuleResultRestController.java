package io.github.kamilkapadia.karabast.components.data.ruleresult;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RuleResultRestController {
	@Autowired
	private RuleResultService ruleResultService;
	
	@GetMapping("/ruleResults/{id}")
	public RuleResult find(@PathVariable long id) {
		return ruleResultService.findById(id);
	}
	
	@GetMapping("/ruleResults")
	public List<RuleResult> findAll() {
		return ruleResultService.findAll();
	}
	
	@GetMapping("/ruleResults/results/{id}")
	public List<RuleResult> findByResultId(@PathVariable long id) {
		return ruleResultService.findByResultId(id);
	}
}

