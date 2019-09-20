package com.sample.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sample.app.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	@Query("SELECT e.firstName, e.lastName " + "FROM Employee e " + "WHERE e.id in :ids ")
	List<Object[]> findFirstAndLastNames(@Param(value = "ids") List<Integer> ids);
}
