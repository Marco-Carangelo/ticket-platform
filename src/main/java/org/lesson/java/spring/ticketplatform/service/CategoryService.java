package org.lesson.java.spring.ticketplatform.service;

import java.util.List;

import org.lesson.java.spring.ticketplatform.model.Category;
import org.lesson.java.spring.ticketplatform.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository repository;
	
	//Service methods
	
	public List<Category> findCategories(){
		
		return repository.findAll();
	}
	
	public Category findCategoryByName(String name) {
		
		return repository.findByNameIgnoreCase(name);
	}
	
	public Category saveCategory(Category category) {
		
		return repository.save(category);
	}

	public void deleteCategoryById(Integer id) {
		
		repository.deleteById(id);
	}
	
	public Category getCategoryById(Integer id) {
		
		return repository.findById(id).get();
	}
}
