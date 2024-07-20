package com.assessment.productcatalog.service.impl;

import java.util.List;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assessment.productcatalog.constant.ProductConstants;
import com.assessment.productcatalog.controller.ProductController;
import com.assessment.productcatalog.document.Product;
import com.assessment.productcatalog.exception.ProductNotFoundException;
import com.assessment.productcatalog.repository.ProductRepository;
import com.assessment.productcatalog.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	private final RabbitTemplate rabbitTemplate;
	private final ProductRepository productRepository;
	
	@Autowired
	public ProductServiceImpl(final RabbitTemplate rabbitTemplate, final ProductRepository productRepository) {
		this.rabbitTemplate = rabbitTemplate;
		this.productRepository = productRepository;
	}

	@Override
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	@Override
	public Product getProductById(String id) {
		return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
	}

	@Override
	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public Product updateProduct(String id, Product productDetails) {
		Product newProduct = getProductById(id);
		newProduct.setName(productDetails.getName());
		newProduct.setDescription(productDetails.getDescription());
		newProduct.setPrice(productDetails.getPrice());
		newProduct.setCategory(productDetails.getCategory());
		newProduct = productRepository.save(newProduct);
		rabbitTemplate.convertAndSend(ProductConstants.PRODUCT_EXCHANGE, ProductConstants.PRODUCT_UPDATED_ROUTE, newProduct.getId());
		return newProduct;
	}

	@Override
	public void deleteProductById(String id) {
		getProductById(id);
		productRepository.deleteById(id);
	}
	

}
