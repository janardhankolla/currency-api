package com.virtusa.currency.rate.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "CURRENCY_DETAILS")

public class CurrencyRateEntity {
	
	public CurrencyRateEntity() {
		
	}
	
	public CurrencyRateEntity(String currencyCode, String currencyJsonResult) {
		super();
		this.currencyCode = currencyCode;
		this.currencyJsonResult = currencyJsonResult;
	}
	
	public CurrencyRateEntity(Long id, String currencyCode, String currencyJsonResult) {
		super();
		this.id = id;
		this.currencyCode = currencyCode;
		this.currencyJsonResult = currencyJsonResult;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@Column(name = "CURRENCY_CODE")
    private String currencyCode;
    
	@Column(name = "CURRENCY_JSON_RESULT")
    private String currencyJsonResult;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getCurrencyJsonResult() {
		return currencyJsonResult;
	}

	public void setCurrencyJsonResult(String currencyJsonResult) {
		this.currencyJsonResult = currencyJsonResult;
	}

	@Override
	public String toString() {
		return "CurrencyRateEntity [id=" + id + ", currencyCode=" + currencyCode + ", currencyJsonResult="
				+ currencyJsonResult + "]";
	}
    
   
}

