package com.sample.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sample.app.entity.Employee;
import com.sample.app.repository.EmployeeRepository;

@RestController
@RequestMapping("api/v1/employees")
public class EmployeeController {
	@Autowired
	public EmployeeRepository empRepo;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Employee>> allEmployees() {
		List<Employee> emps = new ArrayList<> ();
		Iterable<Employee> empsIterable = empRepo.findAll();
		
		for(Employee emp: empsIterable) {
			emps.add(emp);
		}
		return ResponseEntity.ok(emps);
	}

}