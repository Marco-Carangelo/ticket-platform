package org.lesson.java.spring.ticketplatform.service;

import org.lesson.java.spring.ticketplatform.repository.OperatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperatorService {
	
	@Autowired
	private OperatorRepository repository;

}
