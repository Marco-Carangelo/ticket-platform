package org.lesson.java.spring.ticketplatform.service;

import java.util.List;

import org.lesson.java.spring.ticketplatform.model.Operator;
import org.lesson.java.spring.ticketplatform.repository.OperatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperatorService {
	
	@Autowired
	private OperatorRepository repository;
	
	public List<Operator> findActiveOperators(){
		
		return repository.findByInactiveFalse();
	}
	
	public Operator getOperatorByUserId(Integer userId) {
		
		return repository.findByUser(userId);
		
	}
	
	

}
