package com.sample.app.repository;

import java.util.List;

import com.arangodb.springframework.repository.ArangoRepository;
import com.sample.app.entity.Employee;

public interface EmployeeRepository extends ArangoRepository<Employee, String> {
	
	List<Employee> findByAge(Integer age);
	
	List<Employee> findByAgeAndFirstName(Integer age, String firstName);
	
	List<Employee> findByAgeOrFirstName(Integer age, String firstName);

}