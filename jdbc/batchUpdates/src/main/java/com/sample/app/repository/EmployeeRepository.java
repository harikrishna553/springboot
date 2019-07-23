package com.sample.app.repository;

import java.util.List;

import com.sample.app.entity.Employee;

public interface EmployeeRepository {

	public Employee save(Employee emp);
	
	public List<Employee> all();
	
	public Employee get(int id);
	
	public Employee update(Employee emp);
	
	public void update(List<Object[]> emps);
	
	public void printAll();
}
