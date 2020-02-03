package com.sample.app.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "employees")
public class Employee {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "emp_id")
	private int id;
	private String firstName;
	private String lastName;

	@OneToOne(cascade = CascadeType.ALL)
	@JsonManagedReference
	@PrimaryKeyJoinColumn(name="my_emp_acc_id", referencedColumnName = "emp_id")
	private SalaryAccount salaryAccount;

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

	public SalaryAccount getSalaryAccount() {
		return salaryAccount;
	}

	public void setSalaryAccount(SalaryAccount salaryAccount) {
		this.salaryAccount = salaryAccount;
	}

}