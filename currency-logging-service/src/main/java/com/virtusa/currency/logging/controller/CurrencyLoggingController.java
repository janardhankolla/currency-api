package com.virtusa.currency.logging.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.virtusa.currency.logging.service.CurrencyLoggingService;

@RestController
@Scope (value = BeanDefinition.SCOPE_PROTOTYPE)
public class CurrencyLoggingController {
	
	@Autowired
	private CurrencyLoggingService currencyLoggingService;
	
	@PostMapping(value="/api/log")
	public void saveCurrencyDetails(@RequestBody String json,@RequestParam(name="userName") String userName) {
		currencyLoggingService.saveCurrencyDetails(json,userName);
	}

}
