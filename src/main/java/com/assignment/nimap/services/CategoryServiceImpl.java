package com.assignment.nimap.services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.assignment.nimap.entities.Category;
import com.assignment.nimap.entities.Product;
import com.assignment.nimap.repositories.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public List<Category> getAllCategories(int page) {
		Pageable pageable = PageRequest.ofSize(page);
		Page<Category> categories =  categoryRepository.findAll(pageable);
		List<Category> allCategories = categories.getContent();
		return allCategories;
	}

	@Override
	public Category getCategoryById(int id) {
		Optional<Category> category = categoryRepository.findById(id);
		return category.get();
	}

	@Override
	public Category createCategory(Category category) {
		Category newCategory = categoryRepository.findByCategoryName(category.getCategoryName());
		if(newCategory != null)
			return null;
		return categoryRepository.save(category);
	}

	@Override
	public Category updateCategory(int id,Category category) {
		Optional<Category> myCategory = categoryRepository.findById(id);
		if(myCategory!=null) {
			Category existingCategory = myCategory.get();
			existingCategory.setCategoryName(category.getCategoryName());
	        Category updatedCategory = categoryRepository.save(existingCategory);
	        return updatedCategory;
		} else {
			return null;
		}		
	}

	@Override
	public void deleteCategory(int id) {
		categoryRepository.deleteById(id);
	}

}
