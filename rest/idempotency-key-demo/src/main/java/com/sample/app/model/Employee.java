package com.sample.app.model;

import java.util.concurrent.atomic.AtomicInteger;

public class Employee {
	private static final AtomicInteger EMPLOYEE_ID_GENERATOR = new AtomicInteger(0);

	private Integer id;
	private String firstName;
	private String lastName;

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

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public static Employee newEmployee(EmployeeRequestPayload empRequestPayload) {
		Employee emp = new Employee();

		emp.setId(EMPLOYEE_ID_GENERATOR.incrementAndGet());
		emp.setFirstName(empRequestPayload.getFirstName());
		emp.setLastName(empRequestPayload.getLastName());

		return emp;
	}
}
