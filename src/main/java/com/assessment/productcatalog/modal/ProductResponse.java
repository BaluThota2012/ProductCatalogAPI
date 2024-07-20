package com.assessment.productcatalog.modal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {

	public enum Status {
		SUCCESS, FAILURE
	}
	private Status status;
	private String message;
	private Object data;
}
