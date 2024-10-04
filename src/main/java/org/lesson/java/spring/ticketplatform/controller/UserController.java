package org.lesson.java.spring.ticketplatform.controller;

import java.time.LocalDateTime;
import java.util.Optional;

import org.lesson.java.spring.ticketplatform.model.User;
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
@RequestMapping("/user")
public class UserController {
	
	
	@Autowired
	private UserService userService;
	
	// Update method
	
	@GetMapping("/edit/{id}")
	public String edit(Model model, @PathVariable("id") Integer id) {
		
		Optional<User> user = userService.findUserById(id);
		model.addAttribute("user", user);
		
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
		
		userService.updateUser(formUser);
		
		attributes.addFlashAttribute("message","The user " + formUser.getUsername() + " has been updated successfully");
		attributes.addFlashAttribute("alertClass", "alert-success" );
		return "redirect:/operators/" + formUser.getId();
	}

}
