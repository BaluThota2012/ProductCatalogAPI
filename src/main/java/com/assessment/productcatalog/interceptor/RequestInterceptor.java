package com.assessment.productcatalog.interceptor;

import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.MDC;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;

public class RequestInterceptor implements WebRequestInterceptor{

	private static final AtomicInteger INCR = new AtomicInteger();
	
	@Override
	public void preHandle(WebRequest request) throws Exception {
		MDC.put("REQ_ID", String.format("ReqID-%s", INCR.incrementAndGet()));
		MDC.put("CLIENT_INFO", request.getDescription(false));
	}

	@Override
	public void postHandle(WebRequest request, ModelMap model) throws Exception {
		
	}

	@Override
	public void afterCompletion(WebRequest request, Exception ex) throws Exception {
		MDC.remove("REQ_ID");
		MDC.remove("CLIENT_INFO");
	}

}
