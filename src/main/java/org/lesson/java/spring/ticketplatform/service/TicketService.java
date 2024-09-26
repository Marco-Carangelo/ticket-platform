package org.lesson.java.spring.ticketplatform.service;

import java.util.List;

import org.lesson.java.spring.ticketplatform.model.Ticket;
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
	
}
