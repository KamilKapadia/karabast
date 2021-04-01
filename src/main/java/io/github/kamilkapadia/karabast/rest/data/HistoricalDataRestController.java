package io.github.kamilkapadia.karabast.rest.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.kamilkapadia.karabast.dto.data.HistoricalData;
import io.github.kamilkapadia.karabast.service.data.HistoricalDataService;

@RestController
@RequestMapping("/api")
public class HistoricalDataRestController {

	@Autowired
	private HistoricalDataService historicalDataService;
	
	@GetMapping("/historicalData/{id}")
	public HistoricalData find(@PathVariable long id) {
		return historicalDataService.findById(id);
	}
	
	@GetMapping("/historicalData")
	public List<HistoricalData> findAll() {
		return historicalDataService.findAll();
	}
	
	@GetMapping("/historicalData/results/{id}")
	public List<HistoricalData> findByResultId(@PathVariable long id) {
		return historicalDataService.findByResultId(id);
	}
}
