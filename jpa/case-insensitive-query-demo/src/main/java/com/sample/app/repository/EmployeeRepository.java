package com.sample.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sample.app.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	 List<Employee> findByFirstNameIgnoreCase(String firstName);
	 
	 List<Employee> findByLastNameIgnoreCase(String firstName);
	 
	 List<Employee> findByFirstNameContainingIgnoreCase(String firstName);

}