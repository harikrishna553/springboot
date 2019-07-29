package com.sample.app.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sample.app.model.Employee;

@Configuration
public class EmployeeConfiguration {

	@Bean
	public Employee newEmployee() {
		System.out.println("New bean is getting initialized");
		Employee emp = new Employee();
		emp.setId(1);
		emp.setFirstName("Krishna");
		emp.setLastName("Majety");
		return emp;
	}
}
