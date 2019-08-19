package com.sample.app.service;

import java.util.List;

import com.sample.app.entity.Employee;

public interface EmployeeService {

	public List<Employee> allEmployees();
	
	public Iterable<Employee> saveAll(List<Employee> emps);
	
}
