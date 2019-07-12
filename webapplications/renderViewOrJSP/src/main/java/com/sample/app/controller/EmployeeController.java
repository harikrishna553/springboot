package com.sample.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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
	public ResponseEntity<List<Employee>> all() {
		return ResponseEntity.ok(empService.getAllEmployees());
	}

	@RequestMapping(value = "employees", method = RequestMethod.POST)
	public ResponseEntity<Employee> create(@RequestBody Employee emp) {
		return new ResponseEntity<>(empService.createEmployee(emp), HttpStatus.CREATED);

	}

	@RequestMapping(value = "employees/{id}", method = RequestMethod.GET)
	public ResponseEntity<Employee> byId(@PathVariable int id) {
		Optional<Employee> emp = empService.getEmployee(id);

		if (!emp.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return ResponseEntity.ok(emp.get());

	}

	@RequestMapping(value = "employees/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Employee> deleteById(@PathVariable int id) {

		Employee emp = empService.deleteEmployee(id);

		if (emp == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return ResponseEntity.ok(emp);
	}

	@RequestMapping(value = "employees/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Employee> updateById(@PathVariable int id, @RequestBody Employee emp) {

		emp.setId(id);
		Employee updatedEmployee = empService.updateEmployee(emp);

		if (updatedEmployee == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return ResponseEntity.ok(updatedEmployee);
	}

}
