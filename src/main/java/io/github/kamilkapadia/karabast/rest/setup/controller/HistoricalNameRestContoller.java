package io.github.kamilkapadia.karabast.rest.setup.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.kamilkapadia.karabast.dto.setup.HistoricalName;
import io.github.kamilkapadia.karabast.service.setup.HistoricalNameService;

@RestController
@RequestMapping("/api")
public class HistoricalNameRestContoller {
	
	@Autowired
	private HistoricalNameService historicalNameService;
	
	@GetMapping("/historicalnames/{id}")
	public HistoricalName find(@PathVariable long id) {
		return historicalNameService.findById(id);
	}
	
	@GetMapping("/historicalnames")
	public List<HistoricalName> findAll() {
		return historicalNameService.findAll();
	}
	
	@GetMapping("/historicalnames/jobs/{id}")
	public List<HistoricalName> findByJobId(@PathVariable long id) {
		return historicalNameService.findByJobId(id);
	}
}

