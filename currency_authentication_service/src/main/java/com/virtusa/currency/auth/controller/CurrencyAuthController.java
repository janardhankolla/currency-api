package com.virtusa.currency.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.virtusa.currency.auth.dto.UserCredentialsDTO;
import com.virtusa.currency.auth.entity.UserCredentialsEntity;
import com.virtusa.currency.auth.service.CurrencyAuthService;

@RestController
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
@RequestMapping("/api/authenticate")
public class CurrencyAuthController {
	
	@Autowired
	private CurrencyAuthService currencyAuthService;
	
	@PostMapping(value="/addUser",consumes = MediaType.APPLICATION_JSON_VALUE)
	public String addNewUser(@RequestBody UserCredentialsEntity userCredentialsEntity) {
		System.out.println("Input UserCredentialsEntity:"+userCredentialsEntity);
		return currencyAuthService.saveUser(userCredentialsEntity);
	}
	
	@PostMapping(value = "/generate_token",consumes = MediaType.APPLICATION_JSON_VALUE)
	public String generateJwtToken(@RequestBody UserCredentialsDTO userCredentialsDTO) {
		System.out.println("[CurrencyAuthController] Input UserCredentialsDTO :"+userCredentialsDTO);
		return currencyAuthService.generateJwtToken(userCredentialsDTO);
	}
	
	@GetMapping(value = "/validate_token")
	public String validateJwtToken(@RequestParam("token") String token) {
		System.out.println("[CurrencyAuthController] Input Token :"+token);
		currencyAuthService.validateJwtToken(token);
		System.out.println("[CurrencyAuthController] Token is validated successfully :+token");
		return "Token is valid";
	}
	
	
	

}
