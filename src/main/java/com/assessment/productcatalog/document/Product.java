package com.assessment.productcatalog.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("products")
public class Product {
	@Id
	private String id;
	private String name;
	private String description;
	private Double price;
	private String category;
}
