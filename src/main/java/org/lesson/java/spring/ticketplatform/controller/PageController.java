package org.lesson.java.spring.ticketplatform.controller;

import java.util.Set;

import org.lesson.java.spring.ticketplatform.model.Operator;
import org.lesson.java.spring.ticketplatform.model.Role;
import org.lesson.java.spring.ticketplatform.model.User;
import org.lesson.java.spring.ticketplatform.service.OperatorService;
import org.lesson.java.spring.ticketplatform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PageController {
	
	@Autowired
	private OperatorService operatorService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	public String home(Authentication authentication, Model model) {
		
		String username = authentication.getName();
		User user = userService.findUserByUsarname(username).get(); 
		Set<Role> roles = user.getRoles();
		
		
		for(Role r : roles ) {
			if (r.getName().equals("ADMIN"))
				return "redirect:/tickets";
		}
		
		return "redirect:/operators/" + user.getId();
	
	}
	
	
	

	@GetMapping("/operators/{id}")
	public String show(Model model, @PathVariable("id") Integer id) {
		
		Operator operator = operatorService.getOperatorByUserId(id);
				
		model.addAttribute("operator", operator );
		
		return "/operators/show";
	}

}
