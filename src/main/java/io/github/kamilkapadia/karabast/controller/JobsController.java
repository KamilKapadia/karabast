package io.github.kamilkapadia.karabast.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import io.github.kamilkapadia.karabast.dto.setup.Job;
import io.github.kamilkapadia.karabast.service.setup.ContentPathService;
import io.github.kamilkapadia.karabast.service.setup.ActionService;
import io.github.kamilkapadia.karabast.service.setup.HistoricalNameService;
import io.github.kamilkapadia.karabast.service.setup.JobService;
import io.github.kamilkapadia.karabast.service.setup.RuleService;

@Controller
public class JobsController {
	
	@Autowired
	private JobService jobService;
	
	@Autowired
	private RuleService ruleService;
	
	@Autowired
	private HistoricalNameService historicalNameService;
	
	@Autowired
	private ContentPathService contentPathService;
	
	@Autowired
	private ActionService actionService;
	
	
	@GetMapping("/jobs")
	public String jobs(Model theModel) {
		List<Job> jobs = jobService.findAll();
		theModel.addAttribute("jobs", jobs);
		
		return "jobs";
	}
	
	@GetMapping("/job-details")
	public String jobDetails(Model theModel) {
		theModel.addAttribute("rules", ruleService.findByJobId(1));
		theModel.addAttribute("historical", historicalNameService.findByJobId(1));
		theModel.addAttribute("content", contentPathService.findByJobId(1));
		theModel.addAttribute("actions", actionService.findByJobId(1));
		
		return "job-details";
	}
}
