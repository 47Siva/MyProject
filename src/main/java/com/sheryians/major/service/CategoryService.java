package com.sheryians.major.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sheryians.major.entity.Category;
import com.sheryians.major.repository.CategoryRepository;



@Service
public class CategoryService {

	@Autowired
	CategoryRepository categoryRepository;
	
	//chorem
	// show to all gategory in chorem
	public List<Category> getAllCategory(){
		return categoryRepository.findAll();
	}
	
	// store some categorys using in chorem
	public void addCategory(Category Category) {
		categoryRepository.save(Category);
	}
	
	// delete categorys using in chorem
	public void removeCategorysById(int id) {
		categoryRepository.deleteById(id);
	}
	
	// update categorys using in chorem
	public Optional<Category> getCategoryById(int id){
		return categoryRepository.findById(id);
	}

	
	// postman
	// store categors in post man
	public String store(Category category) {
		categoryRepository.save(category);
		return "saved";
	}

	// get all categorys in post man
	public List<Category> getAllCat() {
		return categoryRepository.findAll();
	}

	// delete cat in post man
	public void delete(int id) {
		categoryRepository.deleteById(id);
	}

	//update Cat in post man
	public Category update(Category request) {
		return categoryRepository.save(request);
	}
}
