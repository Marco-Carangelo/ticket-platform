package org.lesson.java.spring.ticketplatform.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
		private String author = "author";
		
		@Size(min = 10, max = 500)
		@NotBlank
		@Column
		private String noteText;
		
		@NotNull
		@Column
		private LocalDateTime createdAt = LocalDateTime.now();
		
		@ManyToOne
		@JoinColumn(name="ticket_id", nullable=false)
		@JsonBackReference
		private Ticket ticket;
		
		//Getters and setters
		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getAuthor() {
			return author;
		}

		public void setAuthor(String author) {
			this.author = author;
		}

		public String getNoteText() {
			return noteText;
		}

		public void setNoteText(String noteText) {
			this.noteText = noteText;
		}

		public LocalDateTime getCreatedAt() {
			return createdAt;
		}

		public void setCreatedAt(LocalDateTime createdAt) {
			this.createdAt = createdAt;
		}

		public Ticket getTicket() {
			return ticket;
		}

		public void setTicket(Ticket ticket) {
			this.ticket = ticket;
		}
		
		

}
