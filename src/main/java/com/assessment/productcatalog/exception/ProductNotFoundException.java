package com.assessment.productcatalog.exception;

public class ProductNotFoundException extends RuntimeException {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5210397521684932633L;

	public ProductNotFoundException(String id) {
		super(getMessage(id));
	}
	
	public ProductNotFoundException(Throwable throwable) {
		super(throwable);
	}
	
	public ProductNotFoundException(String id, Throwable throwable) {
		super(getMessage(id), throwable);
	}
	
	private static String getMessage(String id) {
		return String.format("Product does not exist with ID: %s", id);
	}
}
