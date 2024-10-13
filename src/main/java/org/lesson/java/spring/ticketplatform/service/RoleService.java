package org.lesson.java.spring.ticketplatform.service;

import java.util.List;

import org.lesson.java.spring.ticketplatform.model.Role;
import org.lesson.java.spring.ticketplatform.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
	
	
	@Autowired
	private RoleRepository repository;
	
	//Service methods
	
	public List<Role> findRoles(){
		
		return repository.findAll();
	}
	
}
