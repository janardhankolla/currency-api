package com.virtusa.currencyserviceregistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class CurrencyServiceRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyServiceRegistryApplication.class, args);
	}

}
