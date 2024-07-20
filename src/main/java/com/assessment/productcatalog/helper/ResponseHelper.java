package com.assessment.productcatalog.helper;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.assessment.productcatalog.modal.ProductResponse;
import com.assessment.productcatalog.modal.ProductResponse.Status;

public class ResponseHelper {

	private ResponseHelper() {}
	
	public static ResponseEntity<ProductResponse> sendResponse(Status status, Object data) {
		return sendResponse(status, data, HttpStatus.OK);
	}
	public static ResponseEntity<ProductResponse> sendResponse(Status status, String message, HttpStatus httpStatus) {
		return sendResponse(new ProductResponse(status, message, null), httpStatus);
	}
	public static ResponseEntity<ProductResponse> sendResponse(Status status, Object data, HttpStatus httpStatus){
		return sendResponse(new ProductResponse(status, null, data), httpStatus);
	}
	private static ResponseEntity<ProductResponse> sendResponse(ProductResponse ordersResponse, HttpStatus httpStatus) {
		return new ResponseEntity<>(ordersResponse, httpStatus);
	}
}
