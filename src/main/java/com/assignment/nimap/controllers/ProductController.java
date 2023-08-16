package com.assignment.nimap.controllers;

import java.rmi.ServerException;
import java.util.List;

import javax.persistence.EntityNotFoundException;

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
import org.springframework.web.bind.annotation.RestController;

import com.assignment.nimap.entities.Product;
import com.assignment.nimap.services.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@PostMapping
	public ResponseEntity<Product> createProduct(@RequestBody Product product) throws ServerException {
		Product newProduct = productService.createProduct(product);
		if(newProduct == null) {
			throw new ServerException("Cannot add new product");
		} else {
			return new ResponseEntity<>(newProduct,HttpStatus.CREATED);
		}
	}
	
	@GetMapping
	public ResponseEntity<List<Product>> getAllProducts(
			@RequestParam(value="page", defaultValue="5", required=false) int page
			) {
		List<Product> productList = productService.getAllProducts(page);
		if(productList.isEmpty()) {
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<List<Product>>(productList,HttpStatus.FOUND);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable("id") int id) {
		Product product = productService.getProductById(id);
		if(product==null) {
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(product,HttpStatus.FOUND);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable("id") int id,@RequestBody Product product) {
		Product updatedProduct = productService.updateProduct(id, product);
		if(updatedProduct==null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(updatedProduct,HttpStatus.OK);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable("id") int id) {
		Product product = productService.getProductById(id);
		if(product==null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			productService.deleteProduct(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}
	
}
