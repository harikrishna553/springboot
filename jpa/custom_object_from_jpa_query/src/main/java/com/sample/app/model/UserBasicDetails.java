package com.sample.app.model;

public class UserBasicDetails{
	private Integer id;
	private String firstName;

	public UserBasicDetails(Integer id, String firstName) {
		super();
		this.id = id;
		this.firstName = firstName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

}
