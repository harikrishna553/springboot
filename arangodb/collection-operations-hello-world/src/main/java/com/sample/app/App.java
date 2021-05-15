package com.sample.app;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.arangodb.entity.IndexEntity;
import com.arangodb.springframework.core.ArangoOperations;
import com.arangodb.springframework.core.CollectionOperations;
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
			CollectionOperations empsCollection = arangoTemplate.collection(Employee.class);

			Collection<IndexEntity> collectionIndexes = empsCollection.getIndexes();

			for (IndexEntity indexEntity : collectionIndexes) {
				System.out.println("Index name: " + indexEntity.getName());
				System.out.println("Index type: " + indexEntity.getType());
			}

			empsCollection.drop();

		};
	}
}
