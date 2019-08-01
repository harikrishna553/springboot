package com.sample.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sample.app.model.Employee;
import com.sample.app.service.EmployeeService;

@RestController
@RequestMapping("api/v1/")
public class EmployeeController {

	@Autowired
	private EmployeeService empService;

	@RequestMapping(value = "employees", method = RequestMethod.GET)
	public ResponseEntity<List<Employee>> allEmployees() {
		return ResponseEntity.ok(empService.all());
	}

	@RequestMapping(value = "employees", method = RequestMethod.POST)
	public ResponseEntity<Employee> save(@RequestBody Employee emp) {
		Employee persistedEmp = empService.save(emp);
		return ResponseEntity.status(HttpStatus.CREATED).body(persistedEmp);
	}

}