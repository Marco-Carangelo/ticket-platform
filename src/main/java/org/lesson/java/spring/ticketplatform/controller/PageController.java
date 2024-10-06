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
		
		//Get the roles of the current user
		Set<Role> roles = getCurrentUser( authentication).getRoles();
	
		
		//Search if the current user have the ADMIN authority, if is found redirect to the admin dashboard
		for(Role r : roles ) {
			if (r.getName().equals("ADMIN"))
				return "redirect:/tickets";
		}
		
		//If ADMIN authority is not found the user is redirected to the specific operator page
		return "redirect:/operators/" + getCurrentUser( authentication).getId();
	
	}
	
	
	

	@GetMapping("/operators/{id}")
	public String show(Model model, @PathVariable("id") Integer id) {
		
		Operator operator = operatorService.getOperatorByUserId(id);
				
		model.addAttribute("operator", operator );
		
		return "/operators/show";
	}
	
	
	//Method to get the currently logged user entity
	public User getCurrentUser(Authentication authentication){
		
		String username = authentication.getName();
		return userService.findUserByUsarname(username).get(); 
	}
	
	

}
