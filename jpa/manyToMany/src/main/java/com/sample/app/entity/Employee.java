package com.sample.app.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "employees")
public class Employee {
	@Id
	@GeneratedValue
	@Column(name = "emp_id")
	private int id;
	private String firstName;
	private String lastName;

	@JoinTable(name = "employee_project_mapping", joinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "emp_id"), inverseJoinColumns = @JoinColumn(name = "project_id", referencedColumnName = "pjt_id"))
	@JsonManagedReference
	@ManyToMany(cascade = CascadeType.ALL)
	Set<Project> projects = new HashSet<Project>();

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

	public Set<Project> getProjects() {
		return projects;
	}

	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}

}