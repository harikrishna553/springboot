package com.sample.app;

import java.util.Arrays;
import java.util.List;

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

			System.out.println("Adding two new documents to the employees collection");

			List<Employee> emps = Arrays.asList(new Employee(1, "Ram", "Gurram"),
					new Employee(2, "Sundar", "Deavanagari"));

			employeeRepo.saveAll(emps);

			long noOfDocuments = employeeRepo.count();

			System.out.println("Total number of documents in the collection : " + noOfDocuments);

			System.out.println("\nAdding one more document to the collection");

			employeeRepo.save(new Employee(3, "Kishore", "Munipati"));

			noOfDocuments = employeeRepo.count();

			System.out.println("Total number of documents in the collection : " + noOfDocuments);

		};
	}
}