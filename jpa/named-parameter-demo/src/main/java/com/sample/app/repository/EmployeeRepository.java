package com.sample.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sample.app.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	@Query("SELECT e FROM Employee e WHERE e.firstName = :fName OR e.lastName = :lName")
	public List<Employee> getEmployeesByFirstNameOrLastName(@Param("fName") String firstName,
			@Param("lName") String lastName);
}