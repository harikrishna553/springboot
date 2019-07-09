package com.sample.app;

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

	private static void printEmployees(String message, List<Employee> emps) {
		System.out.println("------------------------------");
		System.out.println(message);
		for (Employee emp : emps) {
			System.out.println(emp);
		}
		System.out.println("------------------------------");
	}

	public static void main(String args[]) {
		SpringApplication.run(App.class, args);
	}

	@Bean
	public CommandLineRunner demo(EmployeeRepository employeeRepository) {
		return (args) -> {
			Employee emp1 = Employee.builder().firstName("Ram").lastName("Gurram").age(32).salary(100000.23).build();
			Employee emp2 = Employee.builder().firstName("Joel").lastName("Chelli").age(43).salary(60000).build();
			Employee emp3 = Employee.builder().firstName("Gopi").lastName("Battu").age(45).salary(1000000).build();
			Employee emp4 = Employee.builder().firstName("Bomma").lastName("Srikanth").age(39).salary(60000).build();
			Employee emp5 = Employee.builder().firstName("Surendra").lastName("Sami").age(32).salary(100000.23).build();

			employeeRepository.save(emp1);
			employeeRepository.save(emp2);
			employeeRepository.save(emp3);
			employeeRepository.save(emp4);
			employeeRepository.save(emp5);

			List<Employee> emps = employeeRepository.findBySalary(60000);
			printEmployees("SELECT * FROM employee WHERE salary=600000", emps);
			
			emps = employeeRepository.findByLastName("Battu");
			printEmployees("SELECT * FROM employee WHERE last_name='Battu'", emps);
			
			emps = employeeRepository.findByAgeGreaterThan(35);
			printEmployees("SELECT * FROM employee WHERE age>35", emps);
			
			emps = employeeRepository.findByAgeAndSalary(32, 100000.23);
			printEmployees("SELECT * FROM employee WHERE age=32 AND salary=100000.23", emps);

		};
	}

}
