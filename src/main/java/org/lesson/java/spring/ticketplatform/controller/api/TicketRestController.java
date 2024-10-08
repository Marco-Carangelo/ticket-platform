package org.lesson.java.spring.ticketplatform.controller.api;

import java.util.List;

import org.lesson.java.spring.ticketplatform.model.Ticket;
import org.lesson.java.spring.ticketplatform.model.Ticket.Status;
import org.lesson.java.spring.ticketplatform.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/tickets")
public class TicketRestController {
	
	@Autowired
	 private TicketService ticketService;
	
	//This method returns all the existing tickets
	@GetMapping
	public List<Ticket> index(){
		
		List<Ticket> result = ticketService.findTickets();
		
		return result;
	}
	
	
	@GetMapping("/{status}")
	public List<Ticket> getTicketsByStatus(@PathVariable("status") String requiredStatus){
		
		
		
		List<Ticket> result = ticketService.findTickets();
		
		return result;
	}
	
	

}
