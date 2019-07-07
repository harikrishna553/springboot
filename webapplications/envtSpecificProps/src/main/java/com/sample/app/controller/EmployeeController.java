package com.sample.app.controller;

import java.util.Collection;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sample.app.model.Employee;
import com.sample.app.util.EmployeeUtil;

@RestController
@RequestMapping("api/v1/")
public class EmployeeController {

	@RequestMapping(value = "employees", method = RequestMethod.GET)
	public ResponseEntity<Collection<Employee>> all() {
		return ResponseEntity.ok(EmployeeUtil.all());
	}

	@RequestMapping(value = "employees", method = RequestMethod.POST)
	public ResponseEntity<Employee> create(@RequestBody Employee emp) {
		return new ResponseEntity<>(EmployeeUtil.create(emp), HttpStatus.CREATED);

	}

	@RequestMapping(value = "employees/{id}", method = RequestMethod.GET)
	public ResponseEntity<Employee> byId(@PathVariable int id) {
		Employee emp = EmployeeUtil.byId(id);

		if (emp == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return ResponseEntity.ok(emp);

	}

	@RequestMapping(value = "employees/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Employee> deleteById(@PathVariable int id) {
		Employee emp = EmployeeUtil.byId(id);

		if (emp == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return ResponseEntity.ok(EmployeeUtil.delete(id));
	}

	@RequestMapping(value = "employees/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Employee> updateById(@PathVariable int id, @RequestBody Employee emp) {

		Employee currentEmp = EmployeeUtil.byId(id);

		if (currentEmp == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return ResponseEntity.ok(EmployeeUtil.updateById(id, emp));
	}

}
