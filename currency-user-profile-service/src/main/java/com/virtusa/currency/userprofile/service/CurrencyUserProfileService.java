package com.virtusa.currency.userprofile.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.virtusa.currency.userprofile.entity.CurrencyRateDetailsEntity;
import com.virtusa.currency.userprofile.repository.CurrencyUserProfileRepository;

@Service
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
public class CurrencyUserProfileService {
	
	@Autowired
	private CurrencyUserProfileRepository currencyUserProfileRepository;
	
	public List<CurrencyRateDetailsEntity> getCurrencyUserProfileDetails(String userName) {
		
		List<CurrencyRateDetailsEntity> list = new ArrayList<>();
		try {
			list = currencyUserProfileRepository.findByUserName(userName);
			
			
		} catch (Exception e) {
			System.out.println("Exception while getting getting user profile details:"+e.toString());
		}
		System.out.println("[CurrencyUserProfileService] DB Response List size :"+list.size()+" Details:"+list);
		return list;
		
	}

}
