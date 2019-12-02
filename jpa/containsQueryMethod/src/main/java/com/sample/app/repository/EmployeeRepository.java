package com.sample.app.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sample.app.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

	// SELECT * FROM employee WHERE salary=X
	public List<Employee> findBySalary(double salary);

	// SELECT * FROM employee WHERE lastName=X
	public List<Employee> findByLastName(String lastName);

	// SELECT * FROM employee WHERE age>X
	public List<Employee> findByAgeGreaterThan(int age);

	// SELECT * FROM employee WHERE age=X AND salary=Y
	public List<Employee> findByAgeAndSalary(int age, double salary);

	// SELECT * FROM employee WHERE firstName LIKE %:nameEndWith
	public List<Employee> findByFirstNameEndingWith(String nameEndWith);

	// SELECT * FROM employee WHERE firstName LIKE :nameStartWith%
	public List<Employee> findByFirstNameStartingWith(String nameStartWith);

	// SELECT * FROM employee WHERE firstName LIKE %:nameStartWith%
	public List<Employee> findByFirstNameContaining(String str);

}
