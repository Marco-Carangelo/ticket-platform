package org.lesson.java.spring.ticketplatform.repository;

import org.lesson.java.spring.ticketplatform.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Integer>  {

}
