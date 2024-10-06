package org.lesson.java.spring.ticketplatform.repository;

import java.util.List;

import org.lesson.java.spring.ticketplatform.model.Operator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OperatorRepository extends JpaRepository<Operator , Integer>   {
	
	
	@Query("FROM Operator o WHERE o.user.id = :userId")
	public Operator findByUser(@Param("userId") Integer userId);

}
