package com.sample.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.arangodb.entity.DocumentCreateEntity;
import com.arangodb.model.DocumentCreateOptions;
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

			Employee emp1 = new Employee(1, "Ram", "Gurram", 31);

			DocumentCreateOptions docCreateOptions = new DocumentCreateOptions();
			docCreateOptions.returnNew(true);

			DocumentCreateEntity docEntity = (DocumentCreateEntity) arangoTemplate.insert(emp1, docCreateOptions);

			System.out.println("Document created");
			System.out.println("id : " + docEntity.getId());
			System.out.println("key : " + docEntity.getKey());
			System.out.println("revision : " + docEntity.getRev());
			System.out.println("doc : " + docEntity.getNew());

		};
	}
}