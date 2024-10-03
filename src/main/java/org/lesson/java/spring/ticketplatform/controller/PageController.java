package org.lesson.java.spring.ticketplatform.controller;

import org.lesson.java.spring.ticketplatform.model.Operator;
import org.lesson.java.spring.ticketplatform.service.OperatorService;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	// Show method
	@GetMapping("/operators/{id}")
	public String show(Model model, @PathVariable("id") Integer id) {
		
		Operator operator = operatorService.getOperatorByUserId(id);
				
		model.addAttribute("operator", operator );
		
		return "/operators/show";
	}

}
