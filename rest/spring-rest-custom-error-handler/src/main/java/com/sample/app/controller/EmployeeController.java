package com.sample.app.controller;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sample.app.model.Employee;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api/v1/employees")
@Api(tags = { "This section contains all Employee Speicifc Operations" })
public class EmployeeController {

	private static AtomicInteger counter = new AtomicInteger();

	private static Map<Integer, Employee> internalCache = new ConcurrentHashMap<>();

	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "Create new employee", notes = "Create new employee")
	public ResponseEntity<Employee> create(@RequestBody @Valid Employee employee) {
		int newId = counter.incrementAndGet();
		employee.setId(newId);

		internalCache.put(newId, employee);

		return ResponseEntity.status(HttpStatus.CREATED).body(employee);
	}

	@RequestMapping(method = RequestMethod.GET)
	@ApiOperation(value = "Get employee by id", notes = "Get employee by  id")
	public ResponseEntity<Employee> create(@RequestParam(name = "empId", required=true) Integer empId) {
		Employee emp = internalCache.get(empId);
		
		if(emp == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(emp);
	}
}
