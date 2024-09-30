package org.lesson.java.spring.ticketplatform.service;

import org.lesson.java.spring.ticketplatform.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository repository;

}
