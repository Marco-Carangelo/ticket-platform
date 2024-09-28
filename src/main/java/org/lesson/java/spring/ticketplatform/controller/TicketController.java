package org.lesson.java.spring.ticketplatform.controller;

import java.time.LocalDateTime;
import org.lesson.java.spring.ticketplatform.model.Ticket;
import org.lesson.java.spring.ticketplatform.model.Ticket.Status;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

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
	
	// Show method
	@GetMapping("/{id}")
	public String show(Model model, @PathVariable("id") Integer id) {
		
		model.addAttribute("ticket", ticketService.findTicketById(id));
		return "/tickets/show";
	}
	
	
	//Create method
	@GetMapping("/create")
	public String create(Model model) {
		
		Ticket ticket = new Ticket();
		model.addAttribute("ticket", ticket);
		
		return "/tickets/create";
		
	}
	
	@PostMapping("/create")
	public String store(
			@Valid @ModelAttribute("ticket") Ticket formTicket,
			BindingResult bindingResult,
			Model model,
			RedirectAttributes attributes ) {
		
		if(bindingResult.hasErrors()) {
			return "/tickets/create";
		}
		
		formTicket.setTicketStatus(Status.OPEN);
		formTicket.setCreatedAt(LocalDateTime.now());
		formTicket.setUpdatedAt(LocalDateTime.now());
		ticketService.createTicket(formTicket);
		
		attributes.addFlashAttribute("message","The ticket " + formTicket.getTicketHeader() + " has been created successfully");
		
		return "redirect:/tickets";
	}
	
	
	// Update method
	
	@GetMapping("/edit/{id}")
	public String edit(Model model, @PathVariable("id") Integer id) {
		
		Ticket ticket = ticketService.findTicketById(id);
		model.addAttribute("ticket", ticket);
		
		return "/tickets/edit";
		
	}
	
	@PostMapping("/edit/{id}")
	public String update(
			@Valid @ModelAttribute("ticket") Ticket formTicket,
			BindingResult bindingResult,
			Model model) {
		
		if(bindingResult.hasErrors()) {
			return "/tickets/edit";
		}
		
		formTicket.setUpdatedAt(LocalDateTime.now());
		ticketService.updateTicket(formTicket);
		
		return "redirect:/tickets";
	}
	
	@PostMapping("/delete/{id}")
	public String delete( @PathVariable("id") Integer id) {
		
		ticketService.deleteTicket(id);
		
		return "redirect:/tickets";
	}

}
