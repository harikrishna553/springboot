package com.sample.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.app.model.Employee;
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

	public Optional<Employee> getEmployee(int id) {
		return employeeRepository.findById(id);
	}

	public Employee createEmployee(Employee emp) {
		return employeeRepository.save(emp);
	}

	public Employee deleteEmployee(int id) {
		Optional<Employee> emp = employeeRepository.findById(id);
		
		if(!emp.isPresent()) {
			return null;
		}
		
		Employee returnedEmp = emp.get();
		employeeRepository.deleteById(id);
		return returnedEmp;
	}

	public Employee updateEmployee(Employee emp) {
		Optional<Employee> persistedEmp = employeeRepository.findById(emp.getId());

		if (!persistedEmp.isPresent()) {
			return null;
		}

		Employee employeeFromDB = persistedEmp.get();
		employeeFromDB.setFirstName(emp.getFirstName());
		employeeFromDB.setLastName(emp.getLastName());

		return employeeRepository.save(employeeFromDB);

	}
}
