package com.sample.app.dto;

public class EmployeeDto {
	private String firstName;
	private String lastName;
	private SalaryAccountDto salaryAccDto;

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

	public SalaryAccountDto getSalaryAccDto() {
		return salaryAccDto;
	}

	public void setSalaryAccDto(SalaryAccountDto salaryAccDto) {
		this.salaryAccDto = salaryAccDto;
	}

}
