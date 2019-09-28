package com.sample.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

@Entity
@Table(name = "employees")
@Where(clause = "IS_DELETED = 'false'")
public class Employee extends BaseEntity {

	@Id
	@GeneratedValue
	@Column(name = "EMPLOYEE_ID")
	private int id;

	@Column(name = "EMPLOYEE_FIRST_NAME")
	private String firstName;

	@Column(name = "EMPLOYEE_LAST_NAME")
	private String lastName;

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
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", getCreatedBy()="
				+ getCreatedBy() + ", getCreatedTime()=" + getCreatedTime() + ", getUpdatedBy()=" + getUpdatedBy()
				+ ", getUpdatedTime()=" + getUpdatedTime() + ", isDeleted()=" + isDeleted() + "]";
	}

}
