package com.assignment.nimap.services;

import java.util.HashSet;
import java.util.List;

import com.assignment.nimap.entities.Category;

public interface CategoryService {
	List<Category> getAllCategories(int page);
	Category getCategoryById(int id);
	Category createCategory(Category category);
	Category updateCategory(int id,Category category);
	void deleteCategory(int id);
}
