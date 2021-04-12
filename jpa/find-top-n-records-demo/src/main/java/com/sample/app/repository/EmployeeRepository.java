package com.sample.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sample.app.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	List<Employee> findTop3ByFirstName(String firstName);

	List<Employee> findTop3ByLastName(String lastName);

	List<Employee> findTop3ByAgeOrFirstName(int age, String firstName);

	List<Employee> findTop3ByFirstNameOrderByLastNameDesc(String firstName);

}
