package com.virtusa.currency.rate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CurrencyRateServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyRateServiceApplication.class, args);
	}

}
