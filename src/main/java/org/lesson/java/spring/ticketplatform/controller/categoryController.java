package org.lesson.java.spring.ticketplatform.controller;

import org.lesson.java.spring.ticketplatform.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/categories")
public class categoryController {
	
	@Autowired
	CategoryService categoryService;
	
	

}
