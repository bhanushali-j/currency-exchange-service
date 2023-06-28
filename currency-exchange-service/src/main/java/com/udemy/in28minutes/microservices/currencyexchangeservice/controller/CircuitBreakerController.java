package com.udemy.in28minutes.microservices.currencyexchangeservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;

@RestController
public class CircuitBreakerController {

	private final Logger logger=LoggerFactory.getLogger(CircuitBreakerController.class);
	
	@GetMapping("/sample-api")
//	@Retry(name = "sample-api", fallbackMethod = "fallbackMethod")
//	@CircuitBreaker(name = "default", fallbackMethod = "fallbackMethod")
	@RateLimiter(name="default")
//	@Bulkhead(name="default") // For concurrent calls
	public String sampleApi() {
		
		logger.info("Sample API Call recieved");
//		ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8080/dummy-url", String.class);
//		
//		return forEntity.getBody();
		
		return "Sample API"; // added to check for rate limiting functionality
		
	}
	
	public String fallbackMethod(Exception ex) {
		return "Fallback Code";
	}
}
