package org.lesson.java.ticketplatform.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tickets")
public class Ticket {
	
	//Definition of the class fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Size(min = 5, max = 50)
	@NotNull
	@Column
	private String ticketHeader;
	
	@Size(min = 10, max = 2000)
	@NotBlank
	@Column
	private String ticketBody;
	
	@NotNull
	@Column
	private Status ticketStatus;
	
	@NotNull
	@Column
	private LocalDateTime createdAt;
	
	@Column 
	LocalDateTime updateAt;
	
	//Enum object with the possible status of a ticket
	private enum Status{
		OPEN,
		ASSIGNED,
		CLOSED
	}

}
