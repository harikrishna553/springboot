package com.sample.app.entity;

public class DepartmentInput {
	private String departmentName;

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DepartmentInput [departmentName=").append(departmentName).append("]");
		return builder.toString();
	}

}
