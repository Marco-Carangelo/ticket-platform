package org.lesson.java.spring.ticketplatform.service;

import org.lesson.java.spring.ticketplatform.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoteService {
	
	@Autowired
	private NoteRepository repository;

}