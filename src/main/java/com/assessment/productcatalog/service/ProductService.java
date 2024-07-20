package com.assessment.productcatalog.service;

import java.util.List;

import com.assessment.productcatalog.document.Product;

public interface ProductService {

	List<Product> getAllProducts();
	
	Product getProductById(String id);
	
	Product saveProduct(Product order);
	
	Product updateProduct(String id, Product order);
	
	void deleteProductById(String id);
}
