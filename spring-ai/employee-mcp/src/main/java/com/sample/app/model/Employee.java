package com.sample.app.model;

import java.time.LocalDate;

/**
 * Core domain model representing an Employee. Every field maps directly to what
 * an HR system would store. The {@code managerId} forms a self-referencing
 * hierarchy:
 *
 * <pre>
 *   CEO (managerId = null)
 *     └── VP (managerId = CEO.id)
 *           └── Director (managerId = VP.id)
 *                 └── Manager (managerId = Director.id)
 *                       └── Employee (managerId = Manager.id)
 * </pre>
 *
 * <p>
 * Jackson serializes this as JSON automatically when Spring AI converts tool
 * results.
 */
public class Employee {

	/** Unique numeric identifier. Primary key. */
	private Long id;

	/** Human-readable employee number (e.g., "EMP-0042"). */
	private String employeeNumber;

	/** First name. */
	private String firstName;

	/** Last name. */
	private String lastName;

	/** Computed full name: "{firstName} {lastName}". */
	private String fullName;

	/** Corporate email address. */
	private String email;

	/** Phone number in international format. */
	private String phone;

	/** Job title (e.g., "Senior Engineer", "Director", "VP"). */
	private String designation;

	/** Business unit (Engineering, Finance, Sales, HR, etc.). */
	private String department;

	/** City of the employee's office. */
	private String city;

	/** State / Province. */
	private String state;

	/** Country of the employee's office. */
	private String country;

	/**
	 * ID of this employee's direct manager. {@code null} for the CEO (top of
	 * hierarchy).
	 */
	private Long managerId;

	/** Full name of the direct manager (denormalized for convenience). */
	private String managerName;

	/** Annual gross salary in USD. */
	private Double salary;

	/** Date the employee joined the company. */
	private LocalDate joiningDate;

	/**
	 * {@code true} if currently employed; {@code false} if on leave or terminated.
	 */
	private boolean active;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmployeeNumber() {
		return employeeNumber;
	}

	public void setEmployeeNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;
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

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Long getManagerId() {
		return managerId;
	}

	public void setManagerId(Long managerId) {
		this.managerId = managerId;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public LocalDate getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(LocalDate joiningDate) {
		this.joiningDate = joiningDate;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
