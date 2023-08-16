package com.assignment.nimap.controllers;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.nimap.entities.Category;
import com.assignment.nimap.services.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@PostMapping
	public ResponseEntity<Category> createCategory(@RequestBody Category category) {
		Category newCategory = categoryService.createCategory(category);
		if(newCategory==null) {
			return new ResponseEntity<Category>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<Category>(newCategory,HttpStatus.CREATED);
		}
	}
	
	@GetMapping
	public ResponseEntity<List<Category>> getAllCategories(
			@RequestParam(value="page", defaultValue="5", required=false) int page
			) {
		List<Category> allCategories = categoryService.getAllCategories(page);
		if(allCategories.isEmpty()) {
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<List<Category>>(allCategories,HttpStatus.FOUND);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Category> getCategoryById(@PathVariable("id") int id){
		Category category = categoryService.getCategoryById(id);
		if(category==null) {
			return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Category>(category,HttpStatus.FOUND);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Category> updateCategory(@PathVariable("id") int id,@RequestBody Category category) {
		Category updatedCategory = categoryService.updateCategory(id, category);
		if(updatedCategory==null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<Category>(updatedCategory,HttpStatus.OK);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCategory(@PathVariable("id") int id) {
		Category category = categoryService.getCategoryById(id);
		if(category==null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			categoryService.deleteCategory(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}
}
