package com.sample.app;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.sample.app.model.Employee;
import com.sample.app.service.EmployeeService;

@SpringBootApplication
public class App {
	public static void main(String args[]) {
		SpringApplication.run(App.class, args);
	}

	@Bean
	public CommandLineRunner demo(EmployeeService employeeService) {
		return (args) -> {
			Employee emp1 = Employee.builder().firstName("Ram").lastName("Gurram").build();
			Employee emp2 = Employee.builder().firstName("Ram").lastName("Gurram").build();
			Employee emp3 = Employee.builder().firstName("Ram").lastName("Gurram").build();
			Employee emp4 = Employee.builder().firstName("Ram").lastName("Gurram").build();

			employeeService.createEmployee(emp1);
			employeeService.createEmployee(emp2);
			employeeService.createEmployee(emp3);
			employeeService.createEmployee(emp4);

			List<Employee> emps = employeeService.getAllEmployees();

			System.out.println("-------------------------");
			System.out.println("Printing Employees");
			System.out.println("-------------------------");
			for (Employee emp : emps) {
				System.out.println(emp);
			}

			System.out.println("Deleting employees");

			employeeService.deleteEmployee(emp1.getId());
			employeeService.deleteEmployee(emp2.getId());
			employeeService.deleteEmployee(emp3.getId());
			employeeService.deleteEmployee(emp4.getId());

			emps = employeeService.getAllEmployees();

			System.out.println("\n\n-------------------------");
			System.out.println("Printing Employees");
			System.out.println("-------------------------");
			for (Employee emp : emps) {
				System.out.println(emp);
			}

		};
	}

}
