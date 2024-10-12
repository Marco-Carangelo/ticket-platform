package org.lesson.java.spring.ticketplatform.controller;

import java.util.Collection;
import org.lesson.java.spring.ticketplatform.service.UserService;
import org.lesson.java.spring.ticketplatform.utils.AuthenticationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PageController {

	
	@Autowired
	private UserService userService;
	
	@GetMapping
	public String home(Authentication authentication, Model model) {
		
		//Retrieve the user authorities
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		
		//Search if the current user have the ADMIN authority, if is found redirect to the admin dashboard
		for(GrantedAuthority a : authorities ) {
			if (a.toString().equals("ADMIN"))
				return "redirect:/tickets";
		}
		
		//If ADMIN authority is not found the user is redirected to the specific operator page
		return "redirect:/operators/" + AuthenticationUtil.getCurrentUser(authentication, userService).getId();
	
	}
	
	

	
	

}
