package org.lesson.java.spring.ticketplatform.controller;

import org.lesson.java.spring.ticketplatform.model.Category;
import org.lesson.java.spring.ticketplatform.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/categories")
public class categoryController {
	
	@Autowired
	CategoryService categoryService;
	
	@GetMapping
	public String index(Model model) {
		
		model.addAttribute("categoryList", categoryService.findCategories());
		return "/categories/index";
		
	}
	
	

}
