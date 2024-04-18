package com.virtusa.currency.auth.repository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.virtusa.currency.auth.configure.CustomUserDetails;
import com.virtusa.currency.auth.entity.UserCredentialsEntity;


@Repository
public class CustomUserDetailsRepository implements UserDetailsService {

	@Autowired
	private CurrencyAuthRepository currencyAuthRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserCredentialsEntity> credential = currencyAuthRepository.findByUserName(username);
		return credential.map(CustomUserDetails::new).orElseThrow(() -> new UsernameNotFoundException("user not found with name :" + username));
	}

}
