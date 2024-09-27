package org.lesson.java.spring.ticketplatform.controller;

import org.lesson.java.spring.ticketplatform.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tickets")
public class TicketController {
	
	@Autowired
	private TicketService ticketService;
	
	@GetMapping
	public String index(Model model) {
		
		model.addAttribute("ticketList", ticketService.findTickets());
		
		return "/tickets/index";
	}

}
