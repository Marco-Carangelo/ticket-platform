package org.lesson.java.spring.ticketplatform.repository;

import org.lesson.java.spring.ticketplatform.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

	
}
