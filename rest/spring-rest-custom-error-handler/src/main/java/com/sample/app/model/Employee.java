package com.sample.app.model;

import javax.validation.constraints.NotNull;

public class Employee {
	private int id;
	
	@NotNull(message = "Firstname can't be null")
	private String firstName;
	
	@NotNull(message = "LastName can't be null")
	private String lastName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

}
