package com.assessment.productcatalog.modal;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties("com.assessment.rabbitmq")
public class RabbitProperties {

	private String username;
	private String password;
	private String vhost;
	private String address;
}
