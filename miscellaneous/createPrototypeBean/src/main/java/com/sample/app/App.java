package com.sample.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.sample.app.configuration.EmployeeConfiguration;
import com.sample.app.model.Employee;

@SpringBootApplication
public class App {
	public static void main(String args[]) {
		System.out.println("Main Application running.......");
		ApplicationContext applicationContext = SpringApplication.run(App.class, args);

		System.out.println("ApplicationContext initialized");

		Employee emp1 = applicationContext.getBean(Employee.class);
		Employee emp2 = applicationContext.getBean(Employee.class);

		EmployeeConfiguration configuration = applicationContext.getBean(EmployeeConfiguration.class);
		Employee emp3 = configuration.newEmployee();
		Employee emp4 = configuration.newEmployee();

		System.out.println("emp1 = " + emp1);
		System.out.println("emp2 = " + emp2);
		System.out.println("emp3 = " + emp3);
		System.out.println("emp4 = " + emp4);
	}
}
