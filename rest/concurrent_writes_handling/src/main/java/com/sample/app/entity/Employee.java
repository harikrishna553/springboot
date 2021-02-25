package com.sample.app.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "employee")
public class Employee implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "employee_id")
	private int id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@UpdateTimestamp
	@Column(name = "LAST_UPD_TS")
	private Timestamp lastUpdateTs;

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

	public static EmployeeBuilder builder() {
		return new EmployeeBuilder();
	}

	public Timestamp getLastUpdateTs() {
		return lastUpdateTs;
	}

	public void setLastUpdateTs(Timestamp lastUpdateTs) {
		this.lastUpdateTs = lastUpdateTs;
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

		public Employee build() {
			return emp;
		}
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + id;
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((lastUpdateTs == null) ? 0 : lastUpdateTs.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id != other.id)
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (lastUpdateTs == null) {
			if (other.lastUpdateTs != null)
				return false;
		} else if (!lastUpdateTs.equals(other.lastUpdateTs))
			return false;
		return true;
	}

}