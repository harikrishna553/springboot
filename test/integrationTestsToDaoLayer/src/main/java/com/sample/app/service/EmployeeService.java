package com.sample.app.service;

import java.util.List;

import com.sample.app.model.Employee;

public interface EmployeeService {
	public Employee save(Employee emp);

	public List<Employee> all();
}
