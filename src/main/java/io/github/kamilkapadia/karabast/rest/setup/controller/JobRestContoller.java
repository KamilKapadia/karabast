package io.github.kamilkapadia.karabast.rest.setup.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.kamilkapadia.karabast.dto.setup.Job;
import io.github.kamilkapadia.karabast.service.setup.JobService;

@RestController
@RequestMapping("/api")
public class JobRestContoller {
	
	@Autowired
	private JobService jobService;
	
//	@Autowired
//	private TypeCodeService typeCodeService;
//	
//	@Autowired
//	private StatusCodeService statusCodeService;
//	
//	@Autowired
//	private RuleCodeService ruleCodeService;
	
	@GetMapping("/jobs/{jobId}")
	public Job find(@PathVariable long jobId) {
		return jobService.findById(jobId);
	}
	
	@GetMapping("/jobs")
	public List<Job> findAll() {
		return jobService.findAll();
	}
	
	@GetMapping("/jobs/names/{name}")
	public List<Job> find(@PathVariable String name) {
		return jobService.findByName(name);
	}
}
