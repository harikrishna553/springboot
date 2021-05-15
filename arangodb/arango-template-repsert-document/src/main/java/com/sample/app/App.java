package com.sample.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.arangodb.entity.DocumentEntity;
import com.arangodb.springframework.core.ArangoOperations;
import com.sample.app.entity.Employee;

@SpringBootApplication
public class App {

	@Autowired
	private ArangoOperations arangoTemplate;

	public static void main(String args[]) {
		SpringApplication.run(App.class, args);
	}

	@Bean
	public CommandLineRunner demo() {

		return (args) -> {
			Employee emp1 = new Employee(1, "Shanmukha Rao", "Kummari");
			Employee emp2 = new Employee(2, "Sailaja", "PTR");

			DocumentEntity docEntity = arangoTemplate.insert(emp1);
			
			arangoTemplate.repsert(emp2);
			
			Iterable<Employee> emps = arangoTemplate.findAll(Employee.class);
			for(Employee emp: emps) {
				System.out.println(emp);
			}
			
			System.out.println("\nReplacing the document using repsert method\n");
			String id = docEntity.getId();
			
			Employee newEmp = new Employee();
			newEmp.setArangoId(id);
			newEmp.setFirstName("Shanmukha Rao Kummari");
			newEmp.setAge(33);
			newEmp.setId(1);
			
			arangoTemplate.repsert(newEmp);
			
			emps = arangoTemplate.findAll(Employee.class);
			for(Employee emp: emps) {
				System.out.println(emp);
			}
		};
	}
}
