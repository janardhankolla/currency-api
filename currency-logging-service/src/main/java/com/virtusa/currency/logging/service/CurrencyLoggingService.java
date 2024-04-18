package com.virtusa.currency.logging.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.virtusa.currency.logging.entitiy.CurrencyDetailsEntity;
import com.virtusa.currency.logging.repository.CurrencyLogginRepository;

@Service
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
public class CurrencyLoggingService {
	
	@Autowired
	private CurrencyLogginRepository currencyLogginRepository;
	
	public void saveCurrencyDetails(String json,String userName) {
		try {	
			CurrencyDetailsEntity currencyDetailsEntity = new CurrencyDetailsEntity(userName,json,LocalDate.now());
			
			currencyDetailsEntity = currencyLogginRepository.save(currencyDetailsEntity);
			System.out.println("[CurrencyLoggingService] Currency details are stored successfully into database :"+currencyDetailsEntity);
			
		}catch(Exception e) {
			System.out.println("[CurrencyLoggingService] Exception while save currency details:"+e.toString());
		}
		
		
		 
	}

}
