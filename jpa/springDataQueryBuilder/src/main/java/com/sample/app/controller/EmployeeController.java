package com.sample.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sample.app.model.Employee;
import com.sample.app.model.SearchQuery;
import com.sample.app.repository.EmployeeRepository;
import com.smaple.app.jpa.specification.SpecificationUtil;

import io.swagger.annotations.ApiOperation;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeRepository empRepo;

	@ApiOperation(value = "Employees Filter", notes = "Get employees by search criteria")
	@PostMapping(value = "/employees", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Employee>> getEmployees(@RequestBody SearchQuery searchQuery) {
		List<Employee> emps = empRepo.findAll(SpecificationUtil.bySearchQuery(searchQuery, Employee.class));

		return new ResponseEntity<>(emps, HttpStatus.OK);
	}

}
