package com.sample.app.repository;

import java.util.List;

import com.sample.app.entity.Employee;

public interface EmployeeCustomRepository {
	List<Employee> emps(List<Integer> empIds);
}
