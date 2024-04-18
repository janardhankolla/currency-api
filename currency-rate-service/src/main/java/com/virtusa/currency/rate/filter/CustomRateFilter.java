package com.virtusa.currency.rate.filter;

import java.io.IOException;
import java.util.Enumeration;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomRateFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
	        throws IOException, ServletException {
		System.out.println("Currency Custom Rate Serivce Filter Invoked");
	    HttpServletRequest request = (HttpServletRequest) req;
	    HttpServletResponse response = (HttpServletResponse) resp;
	
	    Enumeration<String> enumer = request.getHeaderNames();
	    while(enumer.hasMoreElements()) {
	    	String headerName = enumer.nextElement();
	    	
	    	System.out.println("Header Name :"+headerName+" Header value :"+request.getHeader(headerName));
	    }

	    String userAgent = request.getHeader("user-agent");
	    System.out.println("userAgent:"+request.getHeader("host"));
	
	    
	    if (userAgent == null || !userAgent.startsWith("Java")) {
	        RuntimeException unauthorisedException = new RuntimeException("Unauthorized Access Currency Rate Service Directly, you should pass through the API gateway");
	        byte[] responseToSend = unauthorisedException.getMessage().getBytes();
	        ((HttpServletResponse) response).setHeader("Content-Type", "application/json");
	        ((HttpServletResponse) response).setStatus(401);
	        response.getOutputStream().write(responseToSend);
	        return;
	    }
	    chain.doFilter(request, response);
	}

}

