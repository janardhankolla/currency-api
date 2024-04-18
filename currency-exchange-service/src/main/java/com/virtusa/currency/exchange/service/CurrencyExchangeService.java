package com.virtusa.currency.exchange.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.virtusa.currency.exchange.configure.CurrencyExchangeLogingFiegnClient;
import com.virtusa.currency.exchange.configure.CurrencyExchangeRateFiegnClient;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
public class CurrencyExchangeService {
		
	@Autowired
	private CurrencyExchangeRateFiegnClient currencyExchangeFiegnClient;
	
	@Autowired
	private CurrencyExchangeLogingFiegnClient currencyExchangeLogingFiegnClient;
	
	@CircuitBreaker(name="exchangeService",fallbackMethod="getDefaultCurrencyDetails")
	public String getCurrencyExchangeDetails(String jsonReq,String userName) {
		String response = "";
		try {
			Map<String,Object> jsonMap = new GsonJsonParser().parseMap(jsonReq);
			ResponseEntity<String> responseEntity = currencyExchangeFiegnClient.getCurrencyRates((String)jsonMap.get("source_currency"),(String)jsonMap.get("target_currency"));
			System.out.println("Response received from currency-rate-service :"+responseEntity);
		
			response = responseEntity.getBody();
		}catch(Exception e) {
			System.out.println("[CurrencyExchangeService] Exception while getting currency details:"+e.toString());
		}
		return response;
		
	}

	public String getDefaultCurrencyDetails(Exception e) {
		System.out.println("Circuit Breaker fallback method invoked");
		String response = " {\"circuit breaker fallback success response\": true,\"timestamp\": 1713001023,\"historical\": true,\"base\": \"USD\",\"date\": \"2024-04-13\",\"rates\": {\"INR\": 83.61135}}";
		return response;
	}

	public void loggingCurrencyDetails(String response, String userName) {
		
		try {
			currencyExchangeLogingFiegnClient.loggingCurrencyDetails(response, userName);
		}catch(Exception e) {
			System.out.println("[CurrencyExchangeService] Exception while logging the currency details:"+e.toString());
			
		}
	}
}
