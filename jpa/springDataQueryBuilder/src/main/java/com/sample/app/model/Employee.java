package com.sample.app.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "my_employee")
@EntityListeners(AuditingEntityListener.class)
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "age")
	private Integer age;

	@Column(name = "salary")
	private Double salary;

	@CreatedDate
	@Column(name = "created_date")
	private Date createdTime;

	@LastModifiedDate
	@Column(name = "last_modified_date")
	private Date lastModifiedTime;

	@CreatedBy
	private String createdBy;

	@LastModifiedBy
	private String modifiedBy;

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

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public static EmployeeBuilder builder() {
		return new EmployeeBuilder();
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getLastModifiedTime() {
		return lastModifiedTime;
	}

	public void setLastModifiedTime(Date lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}

	public static class EmployeeBuilder {
		private Employee emp;

		public EmployeeBuilder() {
			emp = new Employee();
		}

		public EmployeeBuilder firstName(String firstName) {
			emp.setFirstName(firstName);
			return this;
		}

		public EmployeeBuilder lastName(String lastName) {
			emp.setLastName(lastName);
			return this;
		}

		public EmployeeBuilder age(int age) {
			emp.setAge(age);
			return this;
		}

		public EmployeeBuilder salary(double salary) {
			emp.setSalary(salary);
			return this;
		}

		public Employee build() {
			return emp;
		}
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Employee [id=").append(id).append(", firstName=").append(firstName).append(", lastName=")
				.append(lastName).append(", age=").append(age).append(", salary=").append(salary)
				.append(", createdTime=").append(createdTime).append(", lastModifiedTime=").append(lastModifiedTime)
				.append(", createdBy=").append(createdBy).append(", modifiedBy=").append(modifiedBy).append("]");
		return builder.toString();
	}

}