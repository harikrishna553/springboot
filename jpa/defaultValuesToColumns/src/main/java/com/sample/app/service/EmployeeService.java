package com.sample.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.app.entity.Employee;
import com.sample.app.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	public List<Employee> getAllEmployees() {
		List<Employee> emps = new ArrayList<>();
		employeeRepository.findAll().forEach(emps::add);
		return emps;
	}

	public Employee createEmployee(Employee emp) {
		return employeeRepository.save(emp);
	}

}
