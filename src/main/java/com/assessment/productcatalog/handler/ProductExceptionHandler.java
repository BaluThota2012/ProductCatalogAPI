package com.assessment.productcatalog.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.support.MethodArgumentTypeMismatchException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.assessment.productcatalog.exception.ProductNotFoundException;
import com.assessment.productcatalog.helper.ResponseHelper;
import com.assessment.productcatalog.modal.ProductResponse;
import com.assessment.productcatalog.modal.ProductResponse.Status;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ProductExceptionHandler {

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ProductResponse> methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
		log.error(ex.getMessage(), ex);
		return ResponseHelper.sendResponse(Status.FAILURE, "Invalid ProductID entered!", HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(MissingPathVariableException.class)
	public ResponseEntity<ProductResponse> missingPathVariableExceptionHandler(MissingPathVariableException ex) {
		log.error(ex.getMessage(), ex);
		return ResponseHelper.sendResponse(Status.FAILURE, ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<ProductResponse> requestMethodNotFoundException(HttpRequestMethodNotSupportedException ex) {
		log.error(ex.getMessage(), ex);
		return ResponseHelper.sendResponse(Status.FAILURE, ex.getMessage(), HttpStatus.METHOD_NOT_ALLOWED);
	}
	
	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<ProductResponse> orderExceptionHandler(ProductNotFoundException ex) {
		log.error(ex.getMessage(), ex);
		return ResponseHelper.sendResponse(Status.FAILURE, ex.getMessage(), HttpStatus.OK);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ProductResponse> genericExceptionHandler(Exception ex) {
		log.error(ex.getMessage(), ex);
		return ResponseHelper.sendResponse(Status.FAILURE, "Server error.Please try again later!", HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
