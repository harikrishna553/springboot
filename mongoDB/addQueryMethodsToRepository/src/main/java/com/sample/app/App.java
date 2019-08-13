package com.sample.app;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.MongoClient;
import com.sample.app.entity.Employee;
import com.sample.app.repository.EmployeeRepository;

@SpringBootApplication
public class App {
	@Value("${spring.data.mongodb.database}")
	private String database;

	@Value("${spring.data.mongodb.host}")
	private String host;

	@Value("${spring.data.mongodb.port}")
	private int port;

	@Autowired
	private EmployeeRepository empRepository;

	public static void main(String[] args) {

		SpringApplication.run(App.class, args);
	}

	public void dropPreviousData() {
		MongoClient mongoClient = new MongoClient(host, port);

		MongoOperations mongoOps = new MongoTemplate(mongoClient, database);

		mongoOps.dropCollection("employees");
	}

	public void printAllEmployees(String msg) {
		List<Employee> emps = empRepository.findAll();

		System.out.println("\nAll Employees");
		for (Employee emp : emps) {
			System.out.println(emp);
		}
	}

	public void printAllEmployees(List<Employee> emps, String msg) {

		System.out.println("\n" + msg);
		for (Employee emp : emps) {
			System.out.println(emp);
		}
	}

	@Bean
	public CommandLineRunner demo() {
		return (args) -> {

			dropPreviousData();

			Employee emp1 = new Employee("Phalgun", "Garimella");
			Employee emp2 = new Employee("Sankalp", "Dubey");
			Employee emp3 = new Employee("Arpan", "Garimella");
			Employee emp4 = new Employee("Phalgun", "Dubey");

			empRepository.saveAll(Arrays.asList(emp1, emp2, emp3, emp4));

			List<Employee> emps = empRepository.findByFirstName("Phalgun");
			printAllEmployees(emps, "Employee with first name 'Phalgun'");

			emps = empRepository.findByLastName("Dubey");
			printAllEmployees(emps, "Employee with last name 'Dubey'");

			emps = empRepository.findByFirstNameAndLastName("Phalgun", "Garimella");
			printAllEmployees(emps, "Employee with first name 'Phalgun' and lastName 'Garimella'");

		};
	}
}
