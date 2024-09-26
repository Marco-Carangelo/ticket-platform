package org.lesson.java.spring.ticketplatform.service;

import org.lesson.java.spring.ticketplatform.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketService {

	@Autowired
	private TicketRepository repository;
	
}
