package com.virtusa.currency.auth.dto;

public class UserCredentialsDTO {
	
	private String userName = null;
	private String password = null;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "UserCredentialsDTO [userName=" + userName + ", password=" + password + "]";
	}
	

}
