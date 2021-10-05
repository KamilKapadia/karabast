package io.github.kamilkapadia.karabast.components;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class KarabastRestController {

	@GetMapping("/tests")
	public String tests() {
		return "REST API TEST";
	}
}
