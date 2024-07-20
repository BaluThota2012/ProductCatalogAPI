package com.assessment.productcatalog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.assessment.productcatalog.interceptor.RequestInterceptor;

@Configuration
public class ProductCatalogConfig implements WebMvcConfigurer {
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		WebMvcConfigurer.super.addInterceptors(registry);
		registry.addWebRequestInterceptor(new RequestInterceptor());
	}
	
}
