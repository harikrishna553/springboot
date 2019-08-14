package com.sample.app;

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
import com.sample.app.entity.Employee;

import org.springframework.data.mongodb.core.query.*;

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

			Employee emp1 = new Employee("Phalgun", "Garimella");
			Employee emp2 = new Employee("Sankalp", "Dubey");
			Employee emp3 = new Employee("Arpan", "Garimella");
			Employee emp4 = new Employee("Phalgun", "Dubey");

			mongoTemplate.save(emp1);
			mongoTemplate.save(emp2);
			mongoTemplate.save(emp3);
			mongoTemplate.save(emp4);

			System.out.println("**************************************");
			List<Employee> emps = mongoTemplate.findAll(Employee.class);
			for (Employee emp : emps) {
				System.out.println(emp);
			}

			Query query = new Query();
			query.addCriteria(Criteria.where("firstName").is("Phalgun"));

			Update update = new Update();
			update.set("firstName", "Ram");
			update.set("lastName", "Gurram");

			mongoTemplate.updateMulti(query, update, Employee.class);

			System.out.println("\n**************************************");
			emps = mongoTemplate.findAll(Employee.class);
			for (Employee emp : emps) {
				System.out.println(emp);
			}
		};
	}
}
