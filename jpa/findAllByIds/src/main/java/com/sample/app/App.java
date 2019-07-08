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
			Employee emp2 = Employee.builder().firstName("Shiney").lastName("Soo").build();
			Employee emp3 = Employee.builder().firstName("Mahesh").lastName("Bhat").build();
			Employee emp4 = Employee.builder().firstName("Rahim").lastName("Chelli").build();

			Employee persistedEntity1 = employeeRepository.save(emp1);
			Employee persistedEntity2 = employeeRepository.save(emp2);
			Employee persistedEntity3 = employeeRepository.save(emp3);
			Employee persistedEntity4 = employeeRepository.save(emp4);

			List<Integer> ids = Arrays.asList(232, persistedEntity1.getId(), persistedEntity3.getId(), 56, 90);

			Iterable<Employee> emps = employeeRepository.findAllById(ids);

			for (Employee emp : emps) {
				printEmployee(emp);
			}

		};
	}

}

