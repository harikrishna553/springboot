package com.sample.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "employee_id")
	private int id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "age")
	private int age;

	@Column(name = "salary")
	private double salary;

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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public static EmployeeBuilder builder() {
		return new EmployeeBuilder();
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Employee [id=").append(id).append(", firstName=").append(firstName).append(", lastName=")
				.append(lastName).append(", age=").append(age).append(", salary=").append(salary).append("]");
		return builder.toString();
	}

}
