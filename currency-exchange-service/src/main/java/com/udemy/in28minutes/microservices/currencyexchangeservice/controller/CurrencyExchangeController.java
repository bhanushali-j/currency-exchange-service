package com.udemy.in28minutes.microservices.currencyexchangeservice.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.udemy.in28minutes.microservices.currencyexchangeservice.model.CurrencyExchange;
import com.udemy.in28minutes.microservices.currencyexchangeservice.repository.CurrencyExchangeRepository;

@RestController
public class CurrencyExchangeController {
	
	@Autowired
	CurrencyExchangeRepository currencyExchangeRepository;
	
	@Autowired
	private Environment enviroment; //Spring provides the Environment class to get the environment properties like port server etc
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
//		CurrencyExchange currencyExchange = new CurrencyExchange(1,from,to,BigDecimal.valueOf(50));
		
		
		CurrencyExchange currencyExchange=currencyExchangeRepository.findByFromAndTo(from, to);
		if(currencyExchange==null) {
			throw new RuntimeException("Currency not found for : "+from+" to : "+to);
		}
		String port=enviroment.getProperty("local.server.port");
		currencyExchange.setEnvironment(port);
		return currencyExchange;
		
	}
}
