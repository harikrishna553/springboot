package com.sample.app;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.sample.app.entity.Employee;
import com.sample.app.service.EmployeeService;

@SpringBootApplication
public class App {

	@Autowired
	private EmployeeService empService;

	public static void main(String[] args) {

		SpringApplication.run(App.class, args);
	}

	@Bean
	public CommandLineRunner demo() {
		return (args) -> {

			Employee emp1 = new Employee();
			emp1.setFirstName("Sandeep");
			emp1.setLastName("Maj");

			Employee emp2 = new Employee();
			emp2.setFirstName("Swaroop");
			emp2.setLastName("kdp");

			empService.saveAll(Arrays.asList(emp1, emp2));

			empService.allEmployees().forEach(System.out::println);

		};
	}

}