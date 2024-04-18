package com.virtusa.currency.exchange.filter;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.ErrorResponse;
import org.springframework.web.filter.GenericFilterBean;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomExchangeFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
	        throws IOException, ServletException {
		System.out.println("Currency Exchange Serivce Filter Invoked");
	    HttpServletRequest request = (HttpServletRequest) req;
	    HttpServletResponse response = (HttpServletResponse) resp;
	
	    String proxyForwardedHostHeader = request.getHeader("X-Forwarded-Host");
	    System.out.println("proxyForwardedHostHeader:"+proxyForwardedHostHeader);
	
	    if (proxyForwardedHostHeader == null || !proxyForwardedHostHeader.equals("localhost:9191")) {
	        RuntimeException unauthorisedException = new RuntimeException("Unauthorized Access Currency Exchange Service Directy, you should pass through the API gateway");
	        byte[] responseToSend = unauthorisedException.getMessage().getBytes();
	        ((HttpServletResponse) response).setHeader("Content-Type", "application/json");
	        ((HttpServletResponse) response).setStatus(401);
	        response.getOutputStream().write(responseToSend);
	        return;
	    }
	    chain.doFilter(request, response);
	}

}

