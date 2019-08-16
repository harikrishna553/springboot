package com.sample.app.entity;

public class EmployeeInput {
	private long id;
	private String firstName;
	private String lastName;
	private String email;

	private AddressInput permanentAddress;

	private AddressInput temporaryAddress;

	private DepartmentInput department;

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public AddressInput getPermanentAddress() {
		return permanentAddress;
	}

	public void setPermanentAddress(AddressInput permanentAddress) {
		this.permanentAddress = permanentAddress;
	}

	public AddressInput getTemporaryAddress() {
		return temporaryAddress;
	}

	public void setTemporaryAddress(AddressInput temporaryAddress) {
		this.temporaryAddress = temporaryAddress;
	}

	public DepartmentInput getDepartment() {
		return department;
	}

	public void setDepartment(DepartmentInput department) {
		this.department = department;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EmployeeInput [id=").append(id).append(", firstName=").append(firstName).append(", lastName=")
				.append(lastName).append(", email=").append(email).append(", permanentAddress=")
				.append(permanentAddress).append(", temporaryAddress=").append(temporaryAddress).append(", department=")
				.append(department).append("]");
		return builder.toString();
	}

}
