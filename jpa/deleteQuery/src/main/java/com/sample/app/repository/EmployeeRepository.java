package com.sample.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.sample.app.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	@Transactional
	@Modifying
	@Query("UPDATE Employee SET firstName=UPPER(firstName) WHERE id in :ids")
	public int toUpperEmployeeFirstNames(@Param(value = "ids") List<Integer> ids);

	@Transactional
	@Modifying
	@Query("DELETE FROM Employee WHERE id in :ids")
	public int deleteEmployees(@Param(value = "ids") List<Integer> ids);

}
