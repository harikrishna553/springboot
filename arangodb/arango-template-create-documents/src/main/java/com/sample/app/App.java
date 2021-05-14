package com.sample.app;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.arangodb.entity.DocumentCreateEntity;
import com.arangodb.entity.MultiDocumentEntity;
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
			Employee emp2 = new Employee(2, "Sailaja", "Ptr", 33);
			Employee emp3 = new Employee(3, "Sravya", "Y", 85);

			DocumentCreateOptions docCreateOptions = new DocumentCreateOptions();
			docCreateOptions.returnNew(true);

			MultiDocumentEntity<DocumentCreateEntity> multiDocuments = (MultiDocumentEntity<DocumentCreateEntity>) arangoTemplate
					.insert(Arrays.asList(emp1, emp2, emp3), Employee.class, docCreateOptions);

			Collection<DocumentCreateEntity> documentCreateEntities = multiDocuments.getDocuments();

			System.out.println("Created documents");
			for (DocumentCreateEntity docCreateEntity : documentCreateEntities) {
				System.out.println("\nid : " + docCreateEntity.getId());
				System.out.println("key : " + docCreateEntity.getKey());
				System.out.println("revision : " + docCreateEntity.getRev());
				System.out.println("doc : " + docCreateEntity.getNew());
			}

		};
	}
}