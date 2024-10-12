package org.lesson.java.spring.ticketplatform.model;

import java.util.List;

import org.hibernate.annotations.Formula;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "operators")
public class Operator {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column
	private boolean inactive;
	
	@OneToMany(mappedBy = "operator")
	@JsonBackReference
	private List<Ticket> tickets;
	
	@OneToOne
	@JoinColumn(name = "user_id")
	@JsonBackReference
	private User user;
	

	@Formula("(SELECT count(t.id) " +
			"from  operators o " +
			"inner join tickets t " +
			"on t.operator_id = o.id " +
			"where t.ticket_status <> 'CLOSED' " +
			"and o.id = id )"
			)
	private Integer ticketsToClose;
	
	//Getters and setters

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean isInactive() {
		return inactive;
	}

	public void setInactive(boolean inactive) {
		this.inactive = inactive;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getTicketsToClose() {
		return ticketsToClose;
	}


	
	
	
}
