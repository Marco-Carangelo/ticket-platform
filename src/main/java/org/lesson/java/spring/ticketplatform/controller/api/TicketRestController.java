package org.lesson.java.spring.ticketplatform.controller.api;

import java.util.List;
import java.util.Optional;

import org.lesson.java.spring.ticketplatform.model.Category;
import org.lesson.java.spring.ticketplatform.model.Ticket;
import org.lesson.java.spring.ticketplatform.model.Ticket.Status;
import org.lesson.java.spring.ticketplatform.service.CategoryService;
import org.lesson.java.spring.ticketplatform.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	
	@Autowired
	 private CategoryService categoryService;
	
	//This method returns all the existing tickets
	@GetMapping
	public ResponseEntity<List<Ticket>> index(){
		
		List<Ticket> result = ticketService.findTickets();
		
		if(!result.isEmpty()) {
			return new ResponseEntity<>(result, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	
	}
	
	//Method to filter the tickets by status
	@GetMapping("/{status}")
	public ResponseEntity<List<Ticket>> getTicketsByStatus(@PathVariable("status") String requiredStatus){
		
		List<Ticket> result;
		
		switch(requiredStatus.toUpperCase()) {
		case "OPEN":
			result = ticketService.findTicketByStatus(Status.OPEN);
			break;
		case "IN_PROGRESS":
			result = ticketService.findTicketByStatus(Status.IN_PROGRESS);	
			break;
		case "CLOSED":
			result = ticketService.findTicketByStatus(Status.CLOSED);
			break;
		default:
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	
	
	@GetMapping("/{category}")
	public ResponseEntity<List<Ticket>> getTicketsByCategory(@PathVariable("category") String requestCategory){
		
		List<Ticket> result;
		Optional<Category> category =  Optional.of(categoryService.findCategoryByName(requestCategory));
		
		if(category.isPresent()) {
			result = category.get().getTickets();
			return new ResponseEntity<>(result, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	

}
