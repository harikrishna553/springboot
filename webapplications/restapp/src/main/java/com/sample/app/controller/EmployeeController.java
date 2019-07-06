package com.sample.app.controller;

import java.util.Collection;

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
	public Collection<Employee> all() {
		return EmployeeUtil.all();
	}

	@RequestMapping(value = "employees", method = RequestMethod.POST)
	public Employee create(@RequestBody Employee emp) {
		return EmployeeUtil.create(emp);
	}

	@RequestMapping(value = "employees/{id}", method = RequestMethod.GET)
	public Employee byId(@PathVariable int id) {
		return EmployeeUtil.byId(id);
	}

	@RequestMapping(value = "employees/{id}", method = RequestMethod.DELETE)
	public Employee deleteById(@PathVariable int id) {
		return EmployeeUtil.delete(id);
	}

	@RequestMapping(value = "employees/{id}", method = RequestMethod.PUT)
	public Employee updateById(@PathVariable int id, @RequestBody Employee emp) {
		return EmployeeUtil.updateById(id, emp);
	}

}
