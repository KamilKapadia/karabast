package io.github.kamilkapadia.karabast.controller;

import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class KarabastController {
	
	@GetMapping("/")
	public String karabast(Model theModel) {
		return "karabast";
	}
	
	@GetMapping("/home")
	public String home(Model theModel) {
		
		theModel.addAttribute("theDate", new Date());
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
				
		theModel.addAttribute("username", currentPrincipalName);
		theModel.addAttribute("authorities", authorities);
		
		return "home";
	}
}
