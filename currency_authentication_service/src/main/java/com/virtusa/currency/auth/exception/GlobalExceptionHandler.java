package com.virtusa.currency.auth.exception;

import java.util.Locale;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler({Exception.class})
    protected ResponseEntity<String> handleException(Exception e, Locale locale) {
		System.out.println("Exception in Currency Authenticaton Service Details:"+e.getMessage());
		
        return ResponseEntity
                .badRequest()
                .body("Unable to process your request at this time. Kindly try after some time or contact support team... Error Details:"+e.getMessage());
    }

}
