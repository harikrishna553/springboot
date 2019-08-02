package com.sample.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.app.dao.EmployeeDao;
import com.sample.app.model.Employee;
import com.sample.app.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeDao empDao;

	@Override
	public Employee save(Employee emp) {
		return empDao.save(emp);
	}

	@Override
	public List<Employee> all() {
		return empDao.all();
	}

}
