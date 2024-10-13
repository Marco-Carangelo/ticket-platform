package org.lesson.java.spring.ticketplatform.controller;

import org.lesson.java.spring.ticketplatform.model.Category;
import org.lesson.java.spring.ticketplatform.model.Ticket;
import org.lesson.java.spring.ticketplatform.service.CategoryService;
import org.lesson.java.spring.ticketplatform.service.TicketService;
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
@RequestMapping("/categories")
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	TicketService ticketService;
	
	@GetMapping
	public String index(Model model) {
		
		model.addAttribute("categoryList", categoryService.findCategories());
		return "/categories/index";
		
	}
	
	@GetMapping("/create")
	public String create(Model model) {
		
		Category category = new Category();
		model.addAttribute("category", category);
		
		return "/categories/create";
	}
	
	@PostMapping("/create")
	public String store(
			@Valid @ModelAttribute("category") Category formCategory,
			BindingResult bindingResult,
			Model model,
			RedirectAttributes attributes) {
		
		if(bindingResult.hasErrors()) {
			return "/categories/create";
		}
		
		categoryService.saveCategory(formCategory);
		
		return "redirect:/categories";
	}
	
	@PostMapping("/delete/{id}")
	public String delete( @PathVariable("id") Integer id) {
		
		Category categoryToDelete = categoryService.getCategoryById(id);
		
		//Deleting all the reference of the category to delete inside the tickets
		for (Ticket ticket : categoryToDelete.getTickets()) {
			
			ticket.getCategories().remove(categoryToDelete);
		}
		
		categoryService.deleteCategoryById(id);

		return "redirect:/categories";
	}
	

}
