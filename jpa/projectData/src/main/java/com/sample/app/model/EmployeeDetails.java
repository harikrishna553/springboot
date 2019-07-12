package com.sample.app.model;

public class EmployeeDetails {
	private int empId;
	private String name;
	private String country;

	public EmployeeDetails(int id, String name, String country) {
		this.empId = id;
		this.name = name;
		this.country = country;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EmployeeDetails [empId=").append(empId).append(", name=").append(name).append(", country=")
				.append(country).append("]");
		return builder.toString();
	}

}
