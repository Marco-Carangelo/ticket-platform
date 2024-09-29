package org.lesson.java.spring.ticketplatform.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "notes")
public class Note {
	
	//Definition of the class fields
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Integer id;
		
		@Size(min = 2, max = 50)
		@NotBlank
		@Column
		private String author;
		
		@Size(min = 10, max = 500)
		@NotBlank
		@Column
		private String noteText;
		
		@NotNull
		@Column
		private LocalDateTime createdAt;
		
		@ManyToOne
		@JoinColumn(name="ticket_id", nullable=false)
		private Ticket ticket;
		
		

}
