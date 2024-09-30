package org.lesson.java.spring.ticketplatform.service;

import org.lesson.java.spring.ticketplatform.model.Note;
import org.lesson.java.spring.ticketplatform.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoteService {
	
	@Autowired
	private NoteRepository repository;
	
	public Note createNote(Note note) {
		
		return repository.save(note);
	}

}
