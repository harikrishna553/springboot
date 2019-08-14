package com.sample.app.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("employees")
public class Employee {
	@Id
	private String id;

	@Indexed(name="emp_first_name", direction = IndexDirection.ASCENDING)
	private String firstName;

	private String lastName;

	private List<Address> addresses;

	public Employee() {
	}

	public Employee(String firstName, String lastName, List<Address> addresses) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.addresses = addresses;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Employee [id=").append(id).append(", firstName=").append(firstName).append(", lastName=")
				.append(lastName).append(", addresses=").append(addresses).append("]");
		return builder.toString();
	}

}
