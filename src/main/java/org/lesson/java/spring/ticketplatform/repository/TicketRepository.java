package org.lesson.java.spring.ticketplatform.repository;

import java.util.List;

import org.lesson.java.spring.ticketplatform.model.Ticket;
import org.lesson.java.spring.ticketplatform.model.Ticket.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Integer>  {
	
	public List<Ticket> findByTicketHeaderContainsIgnoreCase(String text);
	
	public List<Ticket> findByTicketStatusEquals(Status status);
	
	
	
}
