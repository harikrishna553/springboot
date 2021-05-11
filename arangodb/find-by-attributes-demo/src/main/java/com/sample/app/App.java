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
			Employee emp1 = new Employee(1, "Ram", "Gurram", 31);
			Employee emp2 = new Employee(2, "Rudra", "Gurram", 32);
			Employee emp3 = new Employee(3, "Gopi", "Battu", 31);
			Employee emp4 = new Employee(4, "Joel", "Chelli", 32);
			Employee emp5 = new Employee(5, "Sailu", "Ptr", 31);
			Employee emp6 = new Employee(6, "Lahari", "Gurram", 23);

			employeeRepo.saveAll(Arrays.asList(emp1, emp2, emp3, emp4, emp5, emp6));

			System.out.println("Employees with age 31");
			List<Employee> emps = employeeRepo.findByAge(31);
			for (Employee emp : emps) {
				System.out.println(emp);
			}

			System.out.println("\nEmployees with age 31 and firstName Ram");
			emps = employeeRepo.findByAgeAndFirstName(31, "Ram");
			for (Employee emp : emps) {
				System.out.println(emp);
			}

			System.out.println("\nEmployees with age 31 and firstName Ram");
			emps = employeeRepo.findByAgeOrFirstName(31, "Joel");
			for (Employee emp : emps) {
				System.out.println(emp);
			}

		};
	}
}