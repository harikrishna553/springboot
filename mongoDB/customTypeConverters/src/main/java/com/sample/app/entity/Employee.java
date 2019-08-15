package com.sample.app.entity;

import java.time.ZonedDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("employees")
public class Employee {
	@Id
	private String id;

	@Field("employee_first_name")
	@Indexed(name = "emp_first_name", direction = IndexDirection.ASCENDING)
	private String firstName;

	@Field("employee_last_name")
	private String lastName;

	@Transient
	private String fullName;

	private ZonedDateTime dateOfBirth;

	public Employee() {
	}

	public Employee(String firstName, String lastName, ZonedDateTime dateOfBirth) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
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

	public String getFullName() {
		return this.firstName + "," + this.lastName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public ZonedDateTime getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(ZonedDateTime dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Employee [id=").append(id).append(", firstName=").append(firstName).append(", lastName=")
				.append(lastName).append(", fullName=").append(fullName).append(", dateOfBirth=").append(dateOfBirth)
				.append("]");
		return builder.toString();
	}

}
