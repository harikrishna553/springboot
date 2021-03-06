package com.sample.app.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.sample.app.model.Employee;

@RepositoryRestResource(path="myemployees")
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

	// SELECT * FROM employee WHERE salary=X
	public List<Employee> findBySalary(double salary);

	// SELECT * FROM employee WHERE lastName=X
	public List<Employee> findByLastName(String lastName);

	// SELECT * FROM employee WHERE age>X
	public List<Employee> findByAgeGreaterThan(int age);

	// SELECT * FROM employee WHERE age=X AND salary=Y
	public List<Employee> findByAgeAndSalary(int age, double salary);

}
