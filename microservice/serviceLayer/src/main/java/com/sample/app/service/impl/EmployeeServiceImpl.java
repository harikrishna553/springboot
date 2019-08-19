package com.sample.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.app.entity.Employee;
import com.sample.app.repository.EmployeeRepository;
import com.sample.app.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository empRepo;

	@Override
	public List<Employee> allEmployees() {

		List<Employee> emps = new ArrayList<>();

		for (Employee emp : empRepo.findAll()) {
			emps.add(emp);
		}

		return emps;
	}

	@Override
	public Iterable<Employee> saveAll(List<Employee> emps) {
		return empRepo.saveAll(emps);
	}

}
