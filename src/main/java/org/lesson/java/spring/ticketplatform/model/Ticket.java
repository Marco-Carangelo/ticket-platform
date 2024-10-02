package org.lesson.java.spring.ticketplatform.model;

import java.time.LocalDateTime;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
	@Column
	private String ticketHeader;
	
	@Size(min = 10, max = 2000)
	@NotBlank
	@Column
	private String ticketBody;
	
	
	@Column
	@Enumerated(EnumType.STRING)
	private Status ticketStatus = Status.OPEN;
	
	@NotNull
	@Column
	private LocalDateTime createdAt;
	
	@Column 
	LocalDateTime updatedAt;
	
	@OneToMany(mappedBy = "ticket")
	private List<Note> notes;
	
	@ManyToMany()
	@JoinTable(
			name = "category_ticket",
			joinColumns = @JoinColumn(name = "ticket_id"),
			inverseJoinColumns = @JoinColumn (name = "category_id")
	)
	@JsonBackReference
	private List<Category> categories;
	
	@ManyToOne
	@JoinColumn(name="operator_id", nullable=false)
	private Operator operator;
	
	//Constructor method
	
	public Ticket() {
		
		this.createdAt = LocalDateTime.now();
	
	}
	
	
	
	//Getters and Setters
	
	public Integer getId() {
		return id;
	}
	



	public void setId(Integer id) {
		this.id = id;
	}




	public String getTicketHeader() {
		return ticketHeader;
	}




	public void setTicketHeader(String ticketHeader) {
		this.ticketHeader = ticketHeader;
	}




	public String getTicketBody() {
		return ticketBody;
	}




	public void setTicketBody(String ticketBody) {
		this.ticketBody = ticketBody;
	}




	public Status getTicketStatus() {
		return ticketStatus;
	}




	public void setTicketStatus(Status ticketStatus) {
		this.ticketStatus = ticketStatus;
	}




	public LocalDateTime getCreatedAt() {
		return createdAt;
	}




	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}




	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}




	public void setUpdatedAt(LocalDateTime updateAt) {
		this.updatedAt = updateAt;
	}

	
	


	public List<Note> getNotes() {
		return notes;
	}



	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}
	
	

	public List<Category> getCategories() {
		return categories;
	}



	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}



	public Operator getOperator() {
		return operator;
	}



	public void setOperator(Operator operator) {
		this.operator = operator;
	}







	//Enum object with the possible status of a ticket
	public enum Status{
		OPEN,
		IN_PROGRESS,
		CLOSED
	}

}
