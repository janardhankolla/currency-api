package com.virtusa.currency.userprofile.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "CURRENCY_LOG_DETAILS")

public class CurrencyRateDetailsEntity {
	
	public CurrencyRateDetailsEntity() {
		
	}

	public CurrencyRateDetailsEntity(String userName, String currencyJsonResult, LocalDate localDate) {
		super();
		this.userName = userName;
		this.currencyJsonResult = currencyJsonResult;
		this.localDate = localDate;
	}

	public CurrencyRateDetailsEntity(Long id, String userName, String currencyJsonResult, LocalDate localDate) {
		super();
		this.id = id;
		this.userName = userName;
		this.currencyJsonResult = currencyJsonResult;
		this.localDate = localDate;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@Column(name = "USER_NAME")
    private String userName;
    
	@Column(name = "CURRENCY_JSON_RESPONSE")
    private String currencyJsonResult;
	
	@Column(name = "TRANSCATION_DATE")
	private LocalDate localDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCurrencyJsonResult() {
		return currencyJsonResult;
	}

	public void setCurrencyJsonResult(String currencyJsonResult) {
		this.currencyJsonResult = currencyJsonResult;
	}

	public LocalDate getLocalDate() {
		return localDate;
	}

	public void setLocalDate(LocalDate localDate) {
		this.localDate = localDate;
	}

	@Override
	public String toString() {
		return "CurrencyRateDetailsEntity [id=" + id + ", userName=" + userName + ", currencyJsonResult="
				+ currencyJsonResult + ", localDate=" + localDate + "]";
	}
   
}

