package com.sample.app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.app.dto.Employee;
import com.sample.app.dto.EmployeeCreateRequestDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/employees")
@Tag(name = "employees", description = "This section contains APIs related to employees")
@CrossOrigin("*")
public class EmployeeController {
	private static final List<Employee> EMPLOYEES = new ArrayList<>();
	private static final AtomicInteger ID_COUNTER = new AtomicInteger(0);

	@PostMapping
	@Operation(summary = "Save the employee")
	public ResponseEntity<Employee> save(@RequestBody EmployeeCreateRequestDto dto) {
		Employee emp = getEmployee(dto);
		EMPLOYEES.add(emp);
		return ResponseEntity.status(HttpStatus.CREATED).body(emp);

	}

	@GetMapping
	@Operation(summary = "Get all the employees")
	public ResponseEntity<EmployeesResponse> save() {
		return ResponseEntity.status(HttpStatus.OK).body(new EmployeesResponse(EMPLOYEES));

	}

	private static Employee getEmployee(EmployeeCreateRequestDto dto) {
		return new Employee(ID_COUNTER.incrementAndGet(), dto.name(), dto.city(), dto.age());
	}

	private static record EmployeesResponse(List<Employee> emps) {

	}

}


