package org.lesson.java.spring.ticketplatform.service;

import java.util.Optional;

import org.lesson.java.spring.ticketplatform.model.User;
import org.lesson.java.spring.ticketplatform.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public Optional<User> findUserById(Integer id) {
		
		return repository.findById(id);
	}
	
	public User updateUser(User user) {
		
		return repository.save(user);
	}
	
	public Optional<User> findUserByUsarname(String username) {
		
		return repository.findByUsername(username);
	}
	
	public User createUser(User user) {
		
		return repository.save(user);
	}

}
