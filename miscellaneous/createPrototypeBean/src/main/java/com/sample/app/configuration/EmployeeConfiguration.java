package com.sample.app.configuration;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.sample.app.model.Employee;

@Configuration
public class EmployeeConfiguration {

	@Bean
	@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public Employee newEmployee() {
		System.out.println("New bean is getting initialized");
		Employee emp = new Employee();
		emp.setId(1);
		emp.setFirstName("Krishna");
		emp.setLastName("Majety");
		return emp;
	}
}
