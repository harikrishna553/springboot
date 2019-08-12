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
import com.sample.app.entity.Address;
import com.sample.app.entity.Employee;
import com.sample.app.repository.AddressRepository;
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

	@Autowired
	private AddressRepository addressRepository;

	public static void main(String[] args) {

		SpringApplication.run(App.class, args);
	}

	public void dropPreviousData() {
		MongoClient mongoClient = new MongoClient(host, port);

		MongoOperations mongoOps = new MongoTemplate(mongoClient, database);

		mongoOps.dropCollection("employees");
		mongoOps.dropCollection("addresses");
	}

	@Bean
	public CommandLineRunner demo() {
		return (args) -> {

			dropPreviousData();

			Address addr1 = new Address("Bangalore", "India");
			Address addr2 = new Address("Hyderabad", "India");
			Address addr3 = new Address("Chennai", "India");

			addr1 = addressRepository.save(addr1);
			addr2 = addressRepository.save(addr2);
			addr3 = addressRepository.save(addr3);

			Employee emp1 = new Employee("Phalgun", "Garimella", addr1.getId());
			Employee emp2 = new Employee("Sankalp", "Dubey", addr2.getId());
			Employee emp3 = new Employee("Arpan", "Debroy", addr3.getId());

			empRepository.saveAll(Arrays.asList(emp1, emp2, emp3));

			List<Employee> emps = empRepository.findAll();

			for (Employee emp : emps) {
				System.out.println("************************************");
				Address addr = addressRepository.findById(emp.getAddressId()).get();
				System.out.println(emp);
				System.out.println(addr);
				System.out.println("************************************\n");
			}

		};
	}
}
