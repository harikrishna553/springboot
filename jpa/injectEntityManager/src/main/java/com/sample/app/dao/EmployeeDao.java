package com.sample.app.dao;

import java.util.List;

import com.sample.app.model.Employee;

public interface EmployeeDao {

	public void save(Employee emp);
	
	public List<Employee> all();
}
