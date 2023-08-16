package com.assignment.nimap.services;

import java.util.List;

import com.assignment.nimap.entities.Product;

public interface ProductService {

	List<Product> getAllProducts(int page);
	Product getProductById(int id);
	Product createProduct(Product product);
	Product updateProduct(int id,Product product);
	void deleteProduct(int id);
}
