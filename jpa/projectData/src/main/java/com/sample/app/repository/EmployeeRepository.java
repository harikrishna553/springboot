package com.sample.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sample.app.model.Employee;
import com.sample.app.model.EmployeeDetails;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
	@Query("SELECT new com.sample.app.model.EmployeeDetails(e.id,e.firstName,a.country) from Employee e,Address a ")
    List<EmployeeDetails> getEmployeeDetails();
}
