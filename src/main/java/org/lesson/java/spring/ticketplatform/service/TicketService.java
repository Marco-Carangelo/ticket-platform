package org.lesson.java.spring.ticketplatform.service;

import java.util.List;

import org.lesson.java.spring.ticketplatform.model.Ticket;
import org.lesson.java.spring.ticketplatform.model.Ticket.Status;
import org.lesson.java.spring.ticketplatform.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketService {

	@Autowired
	private TicketRepository repository;
	
	public List<Ticket> findTickets(){
		
		return repository.findAll();
	}
	
	public List<Ticket> findTicketsContains(String text){
		
		return repository.findByTicketHeaderContainsIgnoreCase(text);
	}
	
	public Ticket findTicketById(Integer id){
		
		return repository.findById(id).get();
	}

	public Ticket createTicket(Ticket ticket) {
	
		return repository.save(ticket);
	}
	
	public Ticket updateTicket(Ticket ticket) {
		
		return repository.save(ticket);
	}
	
	public void deleteTicket(Integer id) {
		repository.deleteById(id);
	}
	
	public List<Ticket> findTicketByStatus(Status status) {
		
		return repository.findByTicketStatusEquals(status);
	}
}
