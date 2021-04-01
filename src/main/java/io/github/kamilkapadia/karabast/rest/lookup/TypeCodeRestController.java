package io.github.kamilkapadia.karabast.rest.lookup;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.kamilkapadia.karabast.dto.lookup.TypeCode;
import io.github.kamilkapadia.karabast.service.lookup.TypeCodeService;

@RestController
@RequestMapping("/api")
public class TypeCodeRestController {

	@Autowired
	private TypeCodeService typeCodeService;
	
	@GetMapping("/typecodes/{id}")
	public TypeCode find(@PathVariable int id) {
		return typeCodeService.findById(id);
	}
	
	@GetMapping("/typecodes")
	public List<TypeCode> findAll() {
		return typeCodeService.findAll();
	}
}
