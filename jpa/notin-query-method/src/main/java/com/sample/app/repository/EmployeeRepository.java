package com.sample.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sample.app.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	// IN queries
	List<Employee> findByIdIn(List<Integer> ids);

	List<Employee> findByIdInOrFirstNameIn(List<Integer> ids, List<String> firstNames);

	List<Employee> findByIdInAndFirstNameIn(List<Integer> ids, List<String> firstNames);

	// NOTIN queries
	List<Employee> findByIdNotIn(List<Integer> ids);

	List<Employee> findByIdNotInOrFirstNameNotIn(List<Integer> ids, List<String> firstNames);

	// NOTIN and IN query
	List<Employee> findByIdNotInAndFirstNameIn(List<Integer> ids, List<String> firstNames);

}
