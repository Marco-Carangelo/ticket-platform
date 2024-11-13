package org.lesson.java.spring.ticketplatform.repository;

import java.util.List;

import org.lesson.java.spring.ticketplatform.model.Ticket;
import org.lesson.java.spring.ticketplatform.utils.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Integer>  {
	
	
	//Method used to filter the tickets by ticket header given a substring
	public List<Ticket> findByTicketHeaderContainsIgnoreCase(String text);
	
	//Method used to filter the tickets by status
	public List<Ticket> findByTicketStatusEquals(Status status);
	
	
	
}
