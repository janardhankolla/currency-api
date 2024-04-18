package com.virtusa.currency.logging.entitiy;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "CURRENCY_LOG_DETAILS")
public class CurrencyDetailsEntity {

	public CurrencyDetailsEntity() {
		
	}
	
	public CurrencyDetailsEntity(Long id, String userName, String currencyJsonResponse, LocalDate date) {
		super();
		this.id = id;
		this.userName = userName;
		this.currencyJsonResponse = currencyJsonResponse;
		this.date = date;
	}

	public CurrencyDetailsEntity(String userName, String currencyJsonResponse, LocalDate date) {
		super();
		this.userName = userName;
		this.currencyJsonResponse = currencyJsonResponse;
		this.date = date;
	}



	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "USER_NAME")
	private String userName;
	
	@Column(name = "CURRENCY_JSON_RESPONSE")
	private String currencyJsonResponse;
	
	@Column(name = "TRANSCATION_DATE")
	private LocalDate date;

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

	public String getCurrencyJsonResponse() {
		return currencyJsonResponse;
	}

	public void setCurrencyJsonResponse(String currencyJsonResponse) {
		this.currencyJsonResponse = currencyJsonResponse;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "CurrencyDetailsEntity [id=" + id + ", userName=" + userName + ", currencyJsonResponse="
				+ currencyJsonResponse + ", date=" + date + "]";
	}
	
}
