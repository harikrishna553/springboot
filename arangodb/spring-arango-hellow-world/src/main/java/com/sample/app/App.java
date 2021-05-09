package com.sample.app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.sample.app.entity.Employee;
import com.sample.app.repository.EmployeeRepository;

@SpringBootApplication
public class App {
	public static void main(String args[]) {
		SpringApplication.run(App.class, args);
	}

	@Bean
	public CommandLineRunner demo(EmployeeRepository employeeRepo) {
		return (args) -> {
			
			Employee emp1 = new Employee(1, "Ram", "Gurram");
			
			emp1 = employeeRepo.save(emp1);
			
			System.out.println(emp1);
			
		};
	}
}
