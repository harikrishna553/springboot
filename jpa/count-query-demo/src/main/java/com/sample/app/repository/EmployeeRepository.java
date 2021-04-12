package com.sample.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sample.app.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	long countByFirstName(String firstName);

	long countByLastName(String lastName);

	long countByAge(int age);

	long countByAgeOrFirstName(int age, String firstName);

}
