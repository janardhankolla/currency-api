package com.virtusa.currency.gateway.filter;

import java.util.List;
import java.util.function.Predicate;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component
public class RouteValidator {

	
	public static List<String> currencyApiEndPoints = List.of(
			"/api/authenticate/addUser",
			"/api/authenticate/generate_token",
			"/eureka"
	);
	
	public Predicate<ServerHttpRequest> isSecured = request -> 
													currencyApiEndPoints.stream()
													.noneMatch(uri -> request.getURI().getPath().contains(uri));
			
}
