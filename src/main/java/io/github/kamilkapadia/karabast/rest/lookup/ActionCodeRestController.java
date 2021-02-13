package io.github.kamilkapadia.karabast.rest.lookup;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.kamilkapadia.karabast.dto.lookup.ActionCode;
import io.github.kamilkapadia.karabast.service.lookup.ActionCodeService;

@RestController
@RequestMapping("/api")
public class ActionCodeRestController {

	@Autowired
	private ActionCodeService actionCodeService;
	
	@GetMapping("/actioncodes/{id}")
	public ActionCode find(@PathVariable int id) {
		return actionCodeService.findById(id);
	}
	
	@GetMapping("/actioncodes")
	public List<ActionCode> findAll() {
		return actionCodeService.findAll();
	}
}
