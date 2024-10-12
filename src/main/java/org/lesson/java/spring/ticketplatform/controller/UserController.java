package org.lesson.java.spring.ticketplatform.controller;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.lesson.java.spring.ticketplatform.model.Operator;
import org.lesson.java.spring.ticketplatform.model.Role;
import org.lesson.java.spring.ticketplatform.model.User;
import org.lesson.java.spring.ticketplatform.service.OperatorService;
import org.lesson.java.spring.ticketplatform.service.RoleService;
import org.lesson.java.spring.ticketplatform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {
	
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private OperatorService operatorService;
	
	//Create method
	
	@GetMapping("/create")
	public String create(Model model) {
		
		User user = new User();
		model.addAttribute("user",user);
		return "/users/create";
		
	}
	
	@PostMapping("/create")
	public String store(
			@Valid @ModelAttribute("user") User formUser,
			BindingResult bindingResult,
			Model model) {
		
		if(bindingResult.hasErrors()) {
			return "/users/create";
		}
		//Save the new user object
		userService.createUser(formUser);
		
		//Assign the new user to an operator
		Operator operator = new Operator();
		User justSavedUser = userService.findUserByUsarname(formUser.getUsername()).get();
		operator.setUser(justSavedUser);
		operatorService.createOperator(operator);
		
		return "redirect:/tickets"; 
	}
	
	// Update method
	
	@GetMapping("/edit/{id}")
	public String edit(Model model, @PathVariable("id") Integer id) {
		
		Optional<User> user = userService.findUserById(id);
		model.addAttribute("user", user.get());
		
		return "/users/edit";
		
	}
	

	@PostMapping("/edit/{id}")
	public String update(
			@Valid @ModelAttribute("user") User formUser,
			BindingResult bindingResult,
			Model model,
			RedirectAttributes attributes) {
		
		if(bindingResult.hasErrors()) {
			return "/users/edit";
		}
		
		//Set the OPERATOR role again for this user
		Set<Role> roles = new HashSet<Role>();
		roles.add(roleService.findRoleByName("OPERATOR"));
		
		formUser.setRoles(roles);
		
		//Add encoding prefix to password
		formUser.setPassword("{noop}" + formUser.getPassword());
		userService.updateUser(formUser);
		
		attributes.addFlashAttribute("message","The user " + formUser.getUsername() + " has been updated successfully");
		attributes.addFlashAttribute("alertClass", "alert-success" );
		return "redirect:/operators/" + formUser.getId();
	}

}
