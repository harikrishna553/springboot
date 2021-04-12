package com.sample.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sample.app.entity.Employee;
import com.sample.app.model.IEmployee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	@Query(value = "select id, first_name,last_name from my_employee", nativeQuery = true)
	List<IEmployee> findAllEmpsViaNativeQuery();

	@Query(value = "select id, first_name,last_name from my_employee where first_name=?1", nativeQuery = true)
	List<IEmployee> findAllEmpsByFirstNameViaNativeQuery(String firstName);

}