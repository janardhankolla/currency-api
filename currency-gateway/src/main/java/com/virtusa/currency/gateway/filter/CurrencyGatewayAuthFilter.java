package com.virtusa.currency.gateway.filter;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import com.virtusa.currency.gateway.util.JwtTokenValidator;

@Component
public class CurrencyGatewayAuthFilter extends AbstractGatewayFilterFactory<CurrencyGatewayAuthFilter.Config> {

    @Autowired
    private RouteValidator validator;

    @Autowired
    private JwtTokenValidator jwtTokenValidator;

    public CurrencyGatewayAuthFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            if (validator.isSecured.test(exchange.getRequest())) {
               
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw new RuntimeException("Missing authorization header");
                }

                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                System.out.println("Header Token :"+authHeader);
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    authHeader = authHeader.substring(7);
                    System.out.println("Modified Header Token :"+authHeader);
                }
                try {
                	System.out.println("Validating Header Token :"+authHeader);
                	jwtTokenValidator.validateToken(authHeader);
                	System.out.println("Successfully validated header token :"+authHeader);
                } catch (Exception e) {
                	System.out.println("[CurrencyGatewayAuthFilter] Exception:"+e);
                    System.out.println("Invalid Access...!");
                    throw new RuntimeException("Un authorized access to currency application service");
                }
            }
            return chain.filter(exchange);
        });
    }

    public static class Config {

    }
}

