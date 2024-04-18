package com.virtusa.currency.exchange.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.virtusa.currency.exchange.service.CurrencyExchangeService;

@RestController
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
public class CurrencyExchangeController {

	@Autowired
	private CurrencyExchangeService currencyExchangeService;
	
	@PostMapping(path="/api/exchange/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public String getCurrencyExchange(@RequestBody String exchangeJsonRequest
			,@RequestHeader(name="userName") String userName) {
		
		System.out.println("CurrencyExchangeController Exchange Json Request :"+exchangeJsonRequest);
		String response = currencyExchangeService.getCurrencyExchangeDetails(exchangeJsonRequest,userName);
		currencyExchangeService.loggingCurrencyDetails(response,userName);
		return response;
	}
}
