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

			List<Employee> emps = Arrays.asList(new Employee(1, "Ram", "Gurram"), new Employee(2, "Gopi", "Battu"),
					new Employee(3, "Sailu", "Ptr"));

			employeeRepo.saveAll(emps);

			Iterable<Employee> savedEmps = employeeRepo.findAll();

			for (Employee emp : savedEmps) {
				System.out.println(emp);
			}

		};
	}
}
