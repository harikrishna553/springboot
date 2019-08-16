package com.sample.app.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "department")
public class Department {
	@Id
	@GeneratedValue
	private long id;
	private String departmentName;

	public Department() {
		this("none");
	}

	public Department(String departmentName) {
		super();
		this.departmentName = departmentName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Department [id=").append(id).append(", departmentName=").append(departmentName).append("]");
		return builder.toString();
	}

}