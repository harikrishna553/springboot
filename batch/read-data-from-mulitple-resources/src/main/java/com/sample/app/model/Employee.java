package com.sample.app.model;

import org.springframework.batch.item.ResourceAware;
import org.springframework.core.io.Resource;

public class Employee implements ResourceAware{
	private int id;
	private String firstName;
	private String lastName;
	
	private Resource resource;

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

	@Override
	public void setResource(Resource resource) {
		this.resource = resource;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Employee [id=");
		builder.append(id);
		builder.append(", firstName=");
		builder.append(firstName);
		builder.append(", lastName=");
		builder.append(lastName);
		builder.append(", resource=");
		builder.append(resource.getFilename());
		builder.append("]");
		return builder.toString();
	}
	
	

}
