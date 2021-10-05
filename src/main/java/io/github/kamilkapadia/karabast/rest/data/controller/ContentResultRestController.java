package io.github.kamilkapadia.karabast.rest.data.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.kamilkapadia.karabast.dto.data.ContentResult;
import io.github.kamilkapadia.karabast.service.data.ContentResultService;

@RestController
@RequestMapping("/api")
public class ContentResultRestController {

	@Autowired
	private ContentResultService contentResultService;

	@GetMapping("/contentresults")
	public List<ContentResult> findAll() {
		return contentResultService.findAll();
	}
	
	@GetMapping("/contentresults/{id}")
	public ContentResult findById(@PathVariable long id) {
		return contentResultService.findById(id);
	}
	
	@GetMapping("/contentresults/results/{id}")
	public List<ContentResult> findByResultId(@PathVariable long id) {
		return contentResultService.findByResultId(id);
	}
}