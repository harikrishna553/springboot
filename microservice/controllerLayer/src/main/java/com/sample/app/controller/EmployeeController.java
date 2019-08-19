package com.sample.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sample.app.entity.Employee;
import com.sample.app.service.EmployeeService;

@RestController
@RequestMapping("api/v1/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService empService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Employee>> allEmployees() {
		return ResponseEntity.ok(empService.allEmployees());
	}
}
