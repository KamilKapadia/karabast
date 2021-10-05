package io.github.kamilkapadia.karabast.rest.data.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.kamilkapadia.karabast.dto.data.LastRun;
import io.github.kamilkapadia.karabast.service.data.LastRunService;

@RestController
@RequestMapping("/api")
public class LastRunRestController {
	@Autowired
	private LastRunService lastRunService;
	
	@GetMapping("/lastRuns/{id}")
	public LastRun find(@PathVariable long id) {
		return lastRunService.findById(id);
	}
	
	@GetMapping("/lastRuns")
	public List<LastRun> findAll() {
		return lastRunService.findAll();
	}
	
	@GetMapping("/lastRuns/jobs/{id}")
	public LastRun findByJobId(@PathVariable long id) {
		return lastRunService.findByJobId(id);
	}
}
	