package com.sample.app.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sample.app.model.Employee;

@Repository
public class EmployeeRepository {

	private static final List<Employee> emps = new ArrayList<>();

	static {
		Employee emp1 = new Employee(1, "Sunil", "Dayanand");
		Employee emp2 = new Employee(2, "Keerthi", "Shetty");
		Employee emp3 = new Employee(3, "Ram", "Anand");
		Employee emp4 = new Employee(4, null, "Anand");
		Employee emp5 = new Employee(5, "Ram", null);
		Employee emp6 = new Employee(6, null, null);

		emps.add(emp1);
		emps.add(emp2);
		emps.add(emp3);
		emps.add(emp4);
		emps.add(emp5);
		emps.add(emp6);
	}

	public List<Employee> all() {
		return emps;
	}

}