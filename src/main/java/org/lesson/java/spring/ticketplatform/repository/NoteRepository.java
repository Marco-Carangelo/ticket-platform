package org.lesson.java.spring.ticketplatform.repository;

import org.lesson.java.spring.ticketplatform.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Integer>  {

}
