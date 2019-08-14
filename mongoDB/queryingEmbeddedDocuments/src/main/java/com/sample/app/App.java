package com.sample.app;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.mongodb.MongoClient;
import com.sample.app.entity.Address;
import com.sample.app.entity.Employee;

@SpringBootApplication
public class App {
	@Value("${spring.data.mongodb.database}")
	private String database;

	@Value("${spring.data.mongodb.host}")
	private String host;

	@Value("${spring.data.mongodb.port}")
	private int port;

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

			MongoClient mongoClient = new MongoClient(host, port);
			MongoTemplate mongoTemplate = new MongoTemplate(mongoClient, database);

			List<Address> emp1Addresses = Arrays.asList(new Address("Bangalore", "India"),
					new Address("Hyderabad", "India"));
			List<Address> emp2Addresses = Arrays.asList(new Address("Hyderabad", "India"));
			List<Address> emp3Addresses = Arrays.asList(new Address("Chennai", "India"),
					new Address("Hyderabad", "India"));
			List<Address> emp4Addresses = Arrays.asList(new Address("Amaravathi", "India"),
					new Address("Ongole", "India"));

			Employee emp1 = new Employee("Phalgun", "Garimella", emp1Addresses);
			Employee emp2 = new Employee("Sankalp", "Dubey", emp2Addresses);
			Employee emp3 = new Employee("Arpan", "Garimella", emp3Addresses);
			Employee emp4 = new Employee("Phalgun", "Dubey", emp4Addresses);

			mongoTemplate.save(emp1);
			mongoTemplate.save(emp2);
			mongoTemplate.save(emp3);
			mongoTemplate.save(emp4);

			List<Employee> emps = mongoTemplate.findAll(Employee.class);
			System.out.println("All Employees");
			System.out.println("**************************************");
			emps.forEach(System.out::println);

			Query query = new Query();
			query.addCriteria(Criteria.where("addresses.city").is("Hyderabad"));

			emps = mongoTemplate.findAllAndRemove(query, Employee.class);
			System.out.println("All Employees whose city is Hyderabad");
			System.out.println("**************************************");
			emps.forEach(System.out::println);
		};
	}
}
