package com.sample.app;

import java.util.*;
import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.arangodb.entity.DocumentCreateEntity;
import com.arangodb.entity.DocumentUpdateEntity;
import com.arangodb.entity.MultiDocumentEntity;
import com.arangodb.model.DocumentCreateOptions;
import com.arangodb.model.DocumentUpdateOptions;
import com.arangodb.springframework.core.ArangoOperations;

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
			Map<String, Object> empDoc1 = new HashMap();
			empDoc1.put("firstName", "Shanmukha Rao");
			empDoc1.put("lastName", "Kummari");
			empDoc1.put("id", 1);

			DocumentCreateOptions docCreateOptions = new DocumentCreateOptions();
			docCreateOptions.returnNew(true);

			DocumentCreateEntity documentCreateEntity = (DocumentCreateEntity) arangoTemplate.insert(empDoc1,
					docCreateOptions);

			System.out.println("\nid : " + documentCreateEntity.getId());
			System.out.println("key : " + documentCreateEntity.getKey());
			System.out.println("revision : " + documentCreateEntity.getRev());
			System.out.println("doc : " + documentCreateEntity.getNew());

			// update firstName and add new age property
			Map<String, Object> newEmpDoc = new HashMap();
			newEmpDoc.put("firstName", "Jaideep");
			newEmpDoc.put("age", 35);

			DocumentUpdateOptions docUpdateOptions = new DocumentUpdateOptions();
			docUpdateOptions.returnNew(true);

			DocumentUpdateEntity documentUpdateEntity = (DocumentUpdateEntity) arangoTemplate
					.update(documentCreateEntity.getId(), newEmpDoc, docUpdateOptions);

			System.out.println("\nage attribute is added and firstName is updated in the document\n");

			System.out.println("\nid : " + documentUpdateEntity.getId());
			System.out.println("key : " + documentUpdateEntity.getKey());
			System.out.println("revision : " + documentUpdateEntity.getRev());
			System.out.println("doc : " + documentUpdateEntity.getNew());

		};
	}
}

