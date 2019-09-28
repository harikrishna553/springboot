package com.sample.app.service;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.sample.app.model.Employee;
import java.util.*;

@Service
@CacheConfig(cacheNames = "myEmployeeCache", keyGenerator = "myEmployeeKeyGenerator")
public class EmployeeService {

	private static List<Employee> emps = new ArrayList<>();

	static {
		emps.add(new Employee(1, "Ram", "Kota"));
		emps.add(new Employee(2, "Raj", "Majety"));
		emps.add(new Employee(3, "PTR", "Navakotla"));
		emps.add(new Employee(4, "Krishna", "Boppana"));
	}

	@Cacheable
	public Employee getEmployeeById(int id) {
		System.out.println("getEmployeeById() is called");

		for (Employee emp : emps) {
			if (id == emp.getId()) {
				return emp;
			}
		}
		return null;
	}

	@Cacheable
	public Employee getEmployeeByFirstName(String name) {
		System.out.println("getEmployeeByFirstName() invoked");

		for (Employee emp : emps) {
			if (name.equals(emp.getFirstName())) {
				return emp;
			}
		}
		return null;
	}
}