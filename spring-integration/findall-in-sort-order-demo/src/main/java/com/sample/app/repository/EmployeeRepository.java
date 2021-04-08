package com.sample.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sample.app.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
	public List<Employee> findAllByOrderByIdAsc();
	
	public List<Employee> findAllByOrderByIdDesc();
	
	public List<Employee> findAllByOrderByFirstNameAscIdDesc();
}