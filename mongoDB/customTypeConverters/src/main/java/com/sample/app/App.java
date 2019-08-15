package com.sample.app;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import com.mongodb.MongoClient;
import com.sample.app.converter.ZonedDateTimeReadConverter;
import com.sample.app.converter.ZonedDateTimeWriteConverter;
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

			ZonedDateTime dateOfBirth1 = ZonedDateTime.of(1965, 5, 15, 12, 5, 43, 1, ZoneOffset.UTC);
			ZonedDateTime dateOfBirth2 = ZonedDateTime.of(1975, 6, 16, 13, 6, 44, 2, ZoneOffset.UTC);
			ZonedDateTime dateOfBirth3 = ZonedDateTime.of(1985, 7, 17, 14, 7, 45, 3, ZoneOffset.UTC);
			ZonedDateTime dateOfBirth4 = ZonedDateTime.of(1995, 8, 18, 15, 8, 46, 4, ZoneOffset.UTC);

			Employee emp1 = new Employee("Phalgun", "Garimella", dateOfBirth1);
			Employee emp2 = new Employee("Sankalp", "Dubey", dateOfBirth2);
			Employee emp3 = new Employee("Arpan", "Garimella", dateOfBirth3);
			Employee emp4 = new Employee("Phalgun", "Dubey", dateOfBirth4);

			empRepository.saveAll(Arrays.asList(emp1, emp2, emp3, emp4));

			List<Employee> emps = empRepository.findAll();
			emps.forEach(System.out::println);

		};
	}

	@Bean
	public MongoCustomConversions customConversions() {
		List<Converter<?, ?>> converters = new ArrayList<>();
		converters.add(new ZonedDateTimeReadConverter());
		converters.add(new ZonedDateTimeWriteConverter());
		return new MongoCustomConversions(converters);
	}
}


