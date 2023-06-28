package com.udemy.in28minutes.microservices.currencyexchangeservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
public class CircuitBreakerController {

	private final Logger logger=LoggerFactory.getLogger(CircuitBreakerController.class);
	
	@GetMapping("/sample-api")
//	@Retry(name = "sample-api", fallbackMethod = "fallbackMethod")
	@CircuitBreaker(name = "sample-api", fallbackMethod = "fallbackMethod")
	public String sampleApi() {
		
		logger.info("Sample API Call recieved");
		ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8080/dummy-url", String.class);
		
		return forEntity.getBody();
		
	}
	
	public String fallbackMethod(Exception ex) {
		return "Fallback Code";
	}
}
