package com.assessment.productcatalog.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.assessment.productcatalog.document.Product;
import com.assessment.productcatalog.helper.ResponseHelper;
import com.assessment.productcatalog.modal.ProductResponse;
import com.assessment.productcatalog.modal.ProductResponse.Status;
import com.assessment.productcatalog.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	private final ProductService productService;
	
	@Autowired
	public ProductController(final ProductService productService) {
		this.productService = productService;
	}
	
	@GetMapping
	public ResponseEntity<ProductResponse> getAllProducts() {
		return ResponseHelper.sendResponse(Status.SUCCESS, productService.getAllProducts());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProductResponse> getProductById(@PathVariable String id) {
		return ResponseHelper.sendResponse(Status.SUCCESS, productService.getProductById(id));
	}
	
	@PostMapping
	public ResponseEntity<ProductResponse> saveProduct(@RequestBody Product order) {
		return ResponseHelper.sendResponse(Status.SUCCESS, productService.saveProduct(order));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ProductResponse> updateProduct(@PathVariable String id, @RequestBody Product order) {
		return ResponseHelper.sendResponse(Status.SUCCESS, productService.updateProduct(id, order));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ProductResponse> deleteProductById(@PathVariable String id) {
		productService.deleteProductById(id);	
		return ResponseHelper.sendResponse(Status.SUCCESS, "Order deleted successfully", HttpStatus.OK);
	}
	
}
