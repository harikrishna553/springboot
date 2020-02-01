package com.sample.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sample.app.service.EmployeeService;

import io.swagger.annotations.ApiOperation;

import com.sample.app.dto.EmployeeDto;
import com.sample.app.entity.*;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService empService;

	@ApiOperation(value = "Create new Employee", notes = "Create new Employee")
	@PostMapping(value = "/employees", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> saveEmployee(@RequestBody EmployeeDto empDto) {

		Employee persistedEmp = empService.save(empDto);

		return new ResponseEntity<>(persistedEmp, HttpStatus.CREATED);
	}

	@ApiOperation(value = "Get Employee", notes = "Get employee by id")
	@GetMapping(value = "/employees/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> getEmployees(@PathVariable final Integer id) {

		Employee persistedEmp = empService.getById(id);

		return new ResponseEntity<>(persistedEmp, HttpStatus.CREATED);
	}

	@ApiOperation(value = "Update Employee", notes = "Update Existing Employee")
	@PutMapping(value = "/employees/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> updateEmployee(@PathVariable("id") int id, 
			@RequestBody EmployeeDto empDto) {

		Employee persistedEmp = empService.update(id, empDto);

		return new ResponseEntity<>(persistedEmp, HttpStatus.OK);
	}

}
