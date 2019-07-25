package com.sample.app.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sample.app.model.Employee;

@Repository
public class EmployeeRepository {

	private static final List<Employee> emps = new ArrayList<> ();
	
	static {
		Employee emp1 = new Employee(1, "Sunil", "Dayanand");
		Employee emp2 = new Employee(1, "Keerthi", "Shetty");
		Employee emp3 = new Employee(1, "Ram", "Anand");
		
		emps.add(emp1);
		emps.add(emp2);
		emps.add(emp3);
		
	}
	
	public List<Employee> all(){
		return emps;
	}
	
}
