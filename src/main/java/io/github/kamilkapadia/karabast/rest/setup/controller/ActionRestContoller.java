package io.github.kamilkapadia.karabast.rest.setup.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.kamilkapadia.karabast.dto.setup.Action;
import io.github.kamilkapadia.karabast.service.setup.ActionService;

@RestController
@RequestMapping("/api")
public class ActionRestContoller {
	
	@Autowired
	private ActionService actionService;
	
	@GetMapping("/actions/{id}")
	public Action find(@PathVariable long id) {
		return actionService.findById(id);
	}
	
	@GetMapping("/actions")
	public List<Action> findAll() {
		return actionService.findAll();
	}
	
	@GetMapping("/actions/jobs/{id}")
	public List<Action> findByJobId(@PathVariable long id) {
		return actionService.findByJobId(id);
	}
}
