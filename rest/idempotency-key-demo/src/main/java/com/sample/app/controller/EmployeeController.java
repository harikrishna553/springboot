package com.sample.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.app.model.Employee;
import com.sample.app.model.EmployeeRequestPayload;
import com.sample.app.model.EmployeesResponse;
import com.sample.app.service.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/employees")
@Tag(name = "employees", description = "This section contains APIs related to employees")
@CrossOrigin("*")
public class EmployeeController {

	@Autowired
	private EmployeeService empService;

	@Parameter(name = "Idempotency-Key", in = ParameterIn.HEADER, description = "Idempotency-Key header", required = true)
	@PostMapping
	@Operation(summary = "Save the employee")
	public ResponseEntity<Employee> save(@RequestBody EmployeeRequestPayload dto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(empService.createEmployee(dto));
	}

	@GetMapping
	@Operation(summary = "Get all employees")
	public ResponseEntity<EmployeesResponse> employees() {
		EmployeesResponse empResponse = empService.emps();
		return ResponseEntity.ok(empResponse);
	}

}
