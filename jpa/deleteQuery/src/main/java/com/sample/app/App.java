package com.sample.app;

import java.util.Arrays;

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
	public CommandLineRunner demo(EmployeeRepository empRepo) {
		return (args) -> {

			Employee emp1 = new Employee("Naresh", "Deva");
			Employee emp2 = new Employee("Nani", "Kulkarni");
			Employee emp3 = new Employee("Ram", "Kishore");

			empRepo.saveAll(Arrays.asList(emp1, emp2, emp3));

			empRepo.findAll().forEach(System.out::println);

			int updatedRecords = empRepo.toUpperEmployeeFirstNames(Arrays.asList(1, 2));
			System.out.println("\nTotal records updated : " + updatedRecords + "\n");

			empRepo.findAll().forEach(System.out::println);

			int deletedRecords = empRepo.deleteEmployees(Arrays.asList(2, 3));
			System.out.println("\nTotal records deleted : " + deletedRecords + "\n");

			empRepo.findAll().forEach(System.out::println);
		};
	}

}
