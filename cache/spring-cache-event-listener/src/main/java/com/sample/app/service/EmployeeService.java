package com.sample.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.sample.app.model.Employee;

@Service
@CacheConfig(cacheNames = "myEmployeeCache")
public class EmployeeService {

	private static List<Employee> emps = new ArrayList<>();

	static {
		emps.add(new Employee(1, "Ram", "Kota"));
		emps.add(new Employee(2, "Raj", "Majety"));
		emps.add(new Employee(3, "PTR", "Navakotla"));
		emps.add(new Employee(4, "Krishna", "Boppana"));
	}

	@Cacheable(key = "{#id}")
	public Employee getEmployeeById(int id) {
		System.out.println("getEmployeeById: Getting the informaiton from internal list");
		
		for (Employee emp : emps) {
			if (id == emp.getId()) {
				return emp;
			}
		}
		return null;
	}

	@Cacheable(key = "{#name}")
	public Employee getEmployeeByFirstName(String name) {
		System.out.println("getEmployeeByFirstName: Getting the informaiton from internal list");
		
		for (Employee emp : emps) {
			if (name.equals(emp.getFirstName())) {
				return emp;
			}
		}
		return null;
	}
}