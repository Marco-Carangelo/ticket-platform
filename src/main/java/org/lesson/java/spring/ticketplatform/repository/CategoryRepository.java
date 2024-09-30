package org.lesson.java.spring.ticketplatform.repository;


import org.lesson.java.spring.ticketplatform.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
