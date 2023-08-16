package com.assignment.nimap.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.assignment.nimap.entities.Product;
import com.assignment.nimap.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<Product> getAllProducts(int page) {
		Pageable pageable = PageRequest.ofSize(page);
		Page<Product> products =  productRepository.findAll(pageable);
		List<Product> allProducts = products.getContent();
		return allProducts;
	}
	
	@Override
	public Product getProductById(int id) {
		Optional<Product> product = productRepository.findById(id);
		return product.get();
	}

	@Override
	public Product createProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public Product updateProduct(int id,Product product) {
		Optional<Product> myProduct = productRepository.findById(id);
		if(myProduct.isPresent()) {
			Product existingProduct = myProduct.get();
			existingProduct.setProductName(product.getProductName());
	        existingProduct.setPrice(product.getPrice());
	        existingProduct.setProductDescription(product.getProductDescription());
	        existingProduct.setCompany(product.getCompany());
	        Product updatedProduct = productRepository.save(existingProduct);
	        return updatedProduct;
		} else {
			return null;
		}
	}

	@Override
	public void deleteProduct(int id) {
		productRepository.deleteById(id);
	}

}
