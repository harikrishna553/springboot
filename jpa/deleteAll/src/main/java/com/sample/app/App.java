package com.sample.app;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.sample.app.model.Employee;
import com.sample.app.repository.EmployeeRepository;

@SpringBootApplication
public class App {

	private static void printEmployee(Employee emp) {
		System.out.println("------------------------------");
		System.out.println("Id : " + emp.getId());
		System.out.println("firstName : " + emp.getFirstName());
		System.out.println("lastName : " + emp.getLastName());
		System.out.println("------------------------------");
	}

	public static void main(String args[]) {
		SpringApplication.run(App.class, args);
	}

	@Bean
	public CommandLineRunner demo(EmployeeRepository employeeRepository) {
		return (args) -> {
			Employee emp1 = Employee.builder().firstName("Ram").lastName("Gurram").build();
			Employee emp2 = Employee.builder().firstName("Joel").lastName("Chelli").build();
			Employee emp3 = Employee.builder().firstName("Gopi").lastName("Battu").build();

			employeeRepository.save(emp1);
			employeeRepository.save(emp2);
			employeeRepository.save(emp3);

			long totalEmployees = employeeRepository.count();

			System.out.println("Total Employees : " + totalEmployees);
			System.out.println("Deleting all the entities");

			employeeRepository.deleteAll();
			totalEmployees = employeeRepository.count();

			System.out.println("Total Employees : " + totalEmployees);
		};
	}

}


