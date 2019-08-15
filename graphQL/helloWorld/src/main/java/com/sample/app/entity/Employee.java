package com.sample.app.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "employees")
public class Employee {
	@Id
	@GeneratedValue
	private long id;
	private String firstName;
	private String lastName;
	private String email;

	@OneToOne(cascade = CascadeType.ALL)
	private Address permanentAddress;

	@OneToOne(cascade = CascadeType.ALL)
	private Address temporaryAddress;

	@OneToOne(cascade = CascadeType.ALL)
	private Department department;

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

	public Address getPermanentAddress() {
		return permanentAddress;
	}

	public void setPermanentAddress(Address permanentAddress) {
		this.permanentAddress = permanentAddress;
	}

	public Address getTemporaryAddress() {
		return temporaryAddress;
	}

	public void setTemporaryAddress(Address temporaryAddress) {
		this.temporaryAddress = temporaryAddress;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Employee [id=").append(id).append(", firstName=").append(firstName).append(", lastName=")
				.append(lastName).append(", email=").append(email).append(", permanentAddress=")
				.append(permanentAddress).append(", temporaryAddress=").append(temporaryAddress).append(", department=")
				.append(department).append("]");
		return builder.toString();
	}

	public static EmployeeBuilder builder() {
		return new EmployeeBuilder();
	}

	public static class EmployeeBuilder {

		Employee emp;

		public EmployeeBuilder() {
			emp = new Employee();
			emp.setPermanentAddress(new Address());
			emp.setTemporaryAddress(new Address());
			emp.setDepartment(new Department());
		}

		public Employee build() {
			return emp;
		}

		public EmployeeBuilder firstName(String firstName) {
			emp.setFirstName(firstName);
			return this;
		}

		public EmployeeBuilder lastName(String lastName) {
			emp.setLastName(lastName);
			return this;
		}

		public EmployeeBuilder email(String email) {
			emp.setEmail(email);
			return this;
		}

		public EmployeeBuilder permanentAddress(Address permanentAddress) {
			emp.setPermanentAddress(permanentAddress);
			return this;
		}

		public EmployeeBuilder temporaryAddress(Address temporaryAddress) {
			emp.setTemporaryAddress(temporaryAddress);
			return this;
		}

		public EmployeeBuilder department(Department department) {
			emp.setDepartment(department);
			return this;
		}

	}

}
