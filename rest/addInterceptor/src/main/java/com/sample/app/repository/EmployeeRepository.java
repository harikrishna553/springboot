package com.sample.app.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sample.app.model.Employee;

@Repository
public class EmployeeRepository {

	private static final List<Employee> emps = new ArrayList<>();

	static {
		Employee emp1 = new Employee(1, "Sunil", "Dayanand", "India");
		Employee emp2 = new Employee(2, "Keerthi", "Shetty", "India");
		Employee emp3 = new Employee(3, "Ram", "Anand", "United States Of America");

		emps.add(emp1);
		emps.add(emp2);
		emps.add(emp3);

	}

	public List<Employee> all() {
		return emps;
	}

}