package com.virtusa.currency.exchange.configure;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="currency-logging-service")
public interface CurrencyExchangeLogingFiegnClient {

	@LoadBalanced
	@GetMapping("/api/log")
	public void loggingCurrencyDetails(@RequestBody String json,@RequestParam(name="userName") String userName);
}
