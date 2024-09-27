package org.lesson.java.spring.ticketplatform.controller;

import java.util.List;

import org.lesson.java.spring.ticketplatform.model.Ticket;
import org.lesson.java.spring.ticketplatform.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/tickets")
public class TicketController {
	
	@Autowired
	private TicketService ticketService;
	
	
	// Index method
	@GetMapping
	public String index(Model model, @RequestParam( name = "searchedText", required = false ) String searchedText) {
		
		if( searchedText != null && !searchedText.isEmpty()) {
			
			model.addAttribute("ticketList", ticketService.findTicketsContains(searchedText));
		}else {
			
			model.addAttribute("ticketList", ticketService.findTickets());
			
		}
		return "/tickets/index";
	}
	
	

}
