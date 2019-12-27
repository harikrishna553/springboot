package com.sample.app;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.sample.app.entity.Employee;
import com.sample.app.service.EmployeeService;

@SpringBootApplication
public class App {
	public static void main(String args[]) {
		SpringApplication.run(App.class, args);
	}

	@Bean
	public CommandLineRunner demo(EmployeeService employeeService) {
		return (args) -> {
			Employee emp1 = new Employee();

			Employee emp2 = new Employee();
			emp2.setFirstName("Ram");

			Employee emp3 = new Employee();
			emp3.setLastName("Gurram");

			Employee emp4 = new Employee();
			emp4.setFirstName("Jagan");
			emp4.setLastName("Gummadi");

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

		};
	}

}
