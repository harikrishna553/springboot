package com.sample.app;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.sample.app.entity.Employee;
import com.sample.app.repository.EmployeeRepository;

@SpringBootApplication
public class App {

	@Autowired
	private EmployeeRepository empRepository;

	public static void main(String[] args) {

		SpringApplication.run(App.class, args);
	}

	@Bean
	public CommandLineRunner demo() {
		return (args) -> {

			Employee emp1 = new Employee("Phalgun", "Garimella");
			Employee emp2 = new Employee("Sankalp", "Dubey");
			Employee emp3 = new Employee("Arpan", "Garimella");
			Employee emp4 = new Employee("Phalgun", "Dubey");

			empRepository.saveAll(Arrays.asList(emp1, emp2, emp3, emp4));

			List<Employee> emps = empRepository.findAll();
			emps.forEach(System.out::println);

		};
	}

}
