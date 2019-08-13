package com.sample.app;

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

	@Bean
	public CommandLineRunner demo() {
		return (args) -> {

			dropPreviousData();

			Employee emp1 = new Employee("Phalgun", "Garimella");

			emp1 = empRepository.save(emp1);

			List<Employee> emps = empRepository.findAll();

			System.out.println("\nAll Employees");
			for (Employee emp : emps) {
				System.out.println(emp);
			}

			emp1.setFirstName("ram");
			emp1.setLastName("Gurram");
			System.out.println("\nTrying to insert emp with same id again");

			empRepository.save(emp1);

			emps = empRepository.findAll();

			System.out.println("\nAll Employees");
			for (Employee emp : emps) {
				System.out.println(emp);
			}

		};
	}
}
