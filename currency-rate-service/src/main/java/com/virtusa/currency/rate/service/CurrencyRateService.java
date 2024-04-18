package com.virtusa.currency.rate.service;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.virtusa.currency.rate.entity.CurrencyRateEntity;
import com.virtusa.currency.rate.enums.CurrencyEnum;
import com.virtusa.currency.rate.repository.CurrencyRateRepository;

@Service
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)

public class CurrencyRateService {
		
	@Autowired 
	private RestTemplate restTemplate;
	
	@Autowired
	private CurrencyRateRepository currencyRateRespository;
	
	private String rates = null;
	 
	public String getCurrencyRates(CurrencyEnum sourceCurrency,CurrencyEnum targetCurrency)
	{ 
		String code = sourceCurrency.toString()+"_"+targetCurrency.toString();
		
		 
		/*
		 * if(CurrencyEnum.INR == targetCurrency) { CurrencyEnum base = sourceCurrency;
		 * List<CurrencyEnum> targets = new ArrayList<>(); targets.add(targetCurrency);
		 * System.out.
		 * println("Targent Currency is INR, getting the latest currency rates from third pary api"
		 * ); String apiKey = "VaYGrZfNJvEU4RKBC3w5tNUgO8eUJucW"; HttpHeaders headers =
		 * new HttpHeaders(); headers.add("apikey", apiKey);
		 * headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		 * final HttpEntity<String> headersEntity = new HttpEntity<>(headers); String
		 * url = "https://api.apilayer.com/exchangerates_data/"; String symbols =
		 * String.join("%2C",
		 * targets.stream().map(CurrencyEnum::name).toArray(String[]::new));
		 * 
		 * url = url + LocalDate.now() + "?symbols=" + symbols + "&base=" + base;
		 * System.out.println("URL:"+url);
		 * 
		 * ResponseEntity<String> responseEntity = restTemplate.exchange(url,
		 * HttpMethod.GET, headersEntity, String.class);
		 * 
		 * rates = responseEntity.getBody(); } else { String response2 =
		 * " {\"success\": true,\"timestamp\": 1713001023,\"historical\": true,\"base\": \"USD\",\"date\": \"2024-04-13\",\"rates\": {\"INR\": 83.61135}}"
		 * ;
		 * 
		 * rates = response2;
		 * 
		 * }
		 */
		
		 CurrencyRateEntity rateEntity = currencyRateRespository.findOneByCurrencyCode(code).orElseGet(() -> saveRatesFromApi(code, sourceCurrency, targetCurrency));
		 System.out.println("rateEntity:"+rateEntity);
		 
		
	     return rateEntity.getCurrencyJsonResult();
	  }
	
	 private CurrencyRateEntity saveRatesFromApi(String code, CurrencyEnum base, CurrencyEnum target) {
		 	 CurrencyRateEntity entity = new CurrencyRateEntity(base.toString()+"_"+target.toString(),"NO_JSON_RESPONSE FROM API");
		 	
		 	  
			  List<CurrencyEnum> targets = new ArrayList<>();
			  targets.add(target);
			  String apiKey = "VaYGrZfNJvEU4RKBC3w5tNUgO8eUJucW";
			  				   
			  HttpHeaders headers = new HttpHeaders();
			  headers.add("apikey", apiKey);
			  headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
			  final HttpEntity<String> headersEntity = new HttpEntity<>(headers);
			  String url = "https://api.apilayer.com/exchangerates_data/";
			  String symbols = String.join("%2C", targets.stream().map(CurrencyEnum::name).toArray(String[]::new));
		        
			  url =  url + LocalDate.now() + "?symbols=" + symbols + "&base=" + base;
			  System.out.println("URL:"+url);
			  
			  ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, headersEntity, String.class);

		      rates = responseEntity.getBody();
		      System.out.println("Response from third party API:"+rates);
		      entity.setCurrencyJsonResult(rates);
	          return currencyRateRespository.save(entity);
	 }
	 
	
	  public void getCurrencyFeign()
	  { 
		  CurrencyEnum base = CurrencyEnum.INR;
		  List<CurrencyEnum> targets = new ArrayList<>();
		  targets.add(CurrencyEnum.USD);
		  targets.add(CurrencyEnum.AED);
		  targets.add(CurrencyEnum.AFN);
		  String apiKey = "VaYGrZfNJvEU4RKBC3w5tNUgO8eUJucW";
		  List<String> responseList =  new ArrayList<>();
		  String symbols = String.join("%2C", targets.stream().map(CurrencyEnum::name).toArray(String[]::new));
		 //List<String> responseList =  currencyRateReceiver.getCurrencyDetails(apiKey,LocalDate.now().toString(),symbols,base.toString()); 
		  System.out.println("Response List:"+responseList);
	  }
	 
	

}
