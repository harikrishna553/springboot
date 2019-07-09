package com.sample.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sample.app.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

	@Query("SELECT e.firstName FROM Employee e")
	List<String> getFirstNames();
	
	@Query(value = "SELECT e.first_name FROM my_employee e", nativeQuery=true)
	List<String> getFirstNamesNativeQuery();
}

