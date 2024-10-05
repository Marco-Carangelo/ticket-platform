package org.lesson.java.spring.ticketplatform.repository;

import org.lesson.java.spring.ticketplatform.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer > {
	
	public Role findByName(String role);
	

}
