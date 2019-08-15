package com.sample.app.resolvers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.sample.app.entity.Employee;
import com.sample.app.repository.EmployeeRepository;

@Component
public class QueryResolver implements GraphQLQueryResolver {
	@Autowired
	public EmployeeRepository empRepo;

	public List<Employee> employees() {
		List<Employee> emps = new ArrayList<>();
		Iterable<Employee> empsIterable = empRepo.findAll();

		for (Employee emp : empsIterable) {
			emps.add(emp);
		}
		return emps;
	}
}
