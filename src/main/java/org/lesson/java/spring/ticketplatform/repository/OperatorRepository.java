package org.lesson.java.spring.ticketplatform.repository;

import org.lesson.java.spring.ticketplatform.model.Operator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperatorRepository extends JpaRepository<Operator , Integer>   {

}
