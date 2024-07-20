package com.assessment.productcatalog;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(scanBasePackages = {"com.assessment.productcatalog"}, exclude = {})
@EnableMongoRepositories
@EnableRabbit
public class ProductCatalogApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductCatalogApplication.class, args);
	}
}
