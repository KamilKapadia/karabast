package io.github.kamilkapadia.karabast.rest.setup.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.kamilkapadia.karabast.dto.setup.ContentPath;
import io.github.kamilkapadia.karabast.service.setup.ContentPathService;

@RestController
@RequestMapping("/api")
public class ContentPathRestController {

	@Autowired
	private ContentPathService contentPathService;
	
	@GetMapping("/contentpaths/{id}")
	public ContentPath find(@PathVariable long id) {
		return contentPathService.findById(id);
	}
	
	@GetMapping("/contentpaths")
	public List<ContentPath> findAll() {
		return contentPathService.findAll();
	}
	
	@GetMapping("/contentpaths/jobs/{id}")
	public List<ContentPath> findByJobId(@PathVariable long id) {
		return contentPathService.findByJobId(id);
	}
}
