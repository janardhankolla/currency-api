package com.virtusa.currency.auth.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.virtusa.currency.auth.dto.UserCredentialsDTO;
import com.virtusa.currency.auth.entity.UserCredentialsEntity;
import com.virtusa.currency.auth.repository.CurrencyAuthRepository;

@Service
@Scope (value = BeanDefinition.SCOPE_PROTOTYPE)
public class CurrencyAuthService { 
	
	@Autowired
	private CurrencyAuthRepository currencyAuthRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
    private JwtTokenService jwtTokenService;
	
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	public String saveUser(UserCredentialsEntity userCredentialsEntity) {
		
		userCredentialsEntity.setPassword(passwordEncoder.encode(userCredentialsEntity.getPassword()));
		
		System.out.println("[CurrencyAuthService] Authentication Input Details :"+userCredentialsEntity);
		try {
			Optional<UserCredentialsEntity> details = currencyAuthRepository.findByUserName(userCredentialsEntity.getUserName());
			UserCredentialsEntity dbUserCredentials = details.get();
			
			if(dbUserCredentials != null && !dbUserCredentials.getUserName().toUpperCase().equals(userCredentialsEntity.getUserName())) {
				System.out.println("User "+userCredentialsEntity.getUserName()+" is already exists!");
				
				return "User "+userCredentialsEntity.getUserName()+" is already exists!";
			}
		}catch(Exception e) {
			System.out.println("Details are not avaialable ind DB or Unable to fetch the existing details:"+e.toString());
		}
			
		currencyAuthRepository.save(userCredentialsEntity);
		System.out.println("User credentials saved successfully into database :"+userCredentialsEntity);
		return "User credentials saved successfully into database";
	}
	
	public String generateJwtToken(UserCredentialsDTO userCredentialsDTO) 
	{
		try {
			Authentication authenctication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userCredentialsDTO.getUserName(), userCredentialsDTO.getPassword()));
			System.out.println("Is authenticated User :"+authenctication.isAuthenticated());
			if(authenctication.isAuthenticated()) {
				String token = jwtTokenService.generateToken(userCredentialsDTO.getUserName());
				System.out.println("Generated JWT Token :"+token);
				return token;
			}else {
				return "Invalid access, Bad credentials";
			}
		}catch(Exception e) {
			System.out.println("Exception while generating token:"+e.toString());
			if(e.getMessage().contains("BadCredentialsException")) {
				return "Invalid access, Bad credentials";
			}
			return "Invalid Access";
		}
		
	}
	
	public void validateJwtToken(String jwtToken) {
		jwtTokenService.validateJwtToken(jwtToken);
    }
	

}
