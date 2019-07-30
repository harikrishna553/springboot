package com.sample.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sample.app.annotations.Loggable;
import com.sample.app.model.Employee;

@RestController
@RequestMapping("api/v1/")
public class EmployeeController {

	private static List<Employee> emps = new ArrayList<>();

	static {
		Employee emp1 = new Employee(1, "ram", "Gurram");
		Employee emp2 = new Employee(2, "Sunil", "Dayananda");
		Employee emp3 = new Employee(3, "krishna", "Majety");

		emps.add(emp1);
		emps.add(emp2);
		emps.add(emp3);
	}

	@Loggable
	@RequestMapping(value = "employees", method = RequestMethod.GET)
	public ResponseEntity<List<Employee>> all() {
		return ResponseEntity.ok(emps);
	}

}