package com.sample.app.dto;

import java.util.List;

public class UserDto {
	private String firstName;
	private String lastName;
	private List<BankAccountDto> accounts;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<BankAccountDto> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<BankAccountDto> accounts) {
		this.accounts = accounts;
	}

	

}
