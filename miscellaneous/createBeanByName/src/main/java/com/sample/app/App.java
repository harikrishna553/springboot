package com.sample.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.sample.app.model.Employee;

@SpringBootApplication
public class App {
	public static void main(String args[]) {
		System.out.println("Main Application running.......");
		ApplicationContext applicationContext = SpringApplication.run(App.class, args);

		System.out.println("ApplicationContext initialized");

		Employee emp1 = applicationContext.getBean("krishna", Employee.class);
		Employee emp2 = applicationContext.getBean("ram", Employee.class);

		emp1.print();
		emp2.print();
	}
}
