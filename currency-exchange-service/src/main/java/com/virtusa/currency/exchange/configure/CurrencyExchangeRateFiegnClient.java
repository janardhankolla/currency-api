package com.virtusa.currency.exchange.configure;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name="currency-rate-service")
public interface CurrencyExchangeRateFiegnClient {
	
	@LoadBalanced
	@GetMapping("/api/rates/{source_currency}/{target_currency}")
	public ResponseEntity<String> getCurrencyRates(@PathVariable("source_currency") String sourceCurrency,@PathVariable("target_currency") String targetCurrency);
	
}