package io.github.kamilkapadia.karabast.rest.data.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.kamilkapadia.karabast.dto.data.Content;
import io.github.kamilkapadia.karabast.service.data.ContentService;

@RestController
@RequestMapping("/api")
public class ContentRestController {

	@Autowired
	private ContentService contentService;
	
	@GetMapping("/contents/{id}")
	public Content find(@PathVariable long id) {
		return contentService.findById(id);
	}
	
	@GetMapping("/contents")
	public List<Content> findAll() {
		return contentService.findAll();
	}
}

