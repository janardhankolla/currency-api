package com.virtusa.currency.logging;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CurrencyLoggingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyLoggingServiceApplication.class, args);
	}

}
