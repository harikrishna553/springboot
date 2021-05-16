package com.sample.app;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.arangodb.entity.IndexEntity;
import com.arangodb.model.GeoIndexOptions;
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
			Employee emp1 = new Employee(1, "Krishna", 29.9792, 31.1342);

			arangoTemplate.insert(emp1);

			Iterable<Employee> empsIterable = arangoTemplate.findAll(Employee.class);

			System.out.println("Documents in employees collection");
			for (Employee emp : empsIterable) {
				System.out.println(emp);
			}

			CollectionOperations empsCollection = arangoTemplate.collection(Employee.class);

			GeoIndexOptions gersistentIndexOptions = new GeoIndexOptions();
			gersistentIndexOptions.name("locationIndex");

			empsCollection.ensureGeoIndex(Arrays.asList("locationLongitude", "locationLatitude"),
					gersistentIndexOptions);

			Collection<IndexEntity> indexesCollection = empsCollection.getIndexes();

			System.out.println("\nTotal indexes : " + indexesCollection.size());

			for (IndexEntity indexEntity : indexesCollection) {
				System.out.println("\nindex details");
				System.out.println("name : " + indexEntity.getName());
				System.out.println("Index created on fields : " + indexEntity.getFields());
			}

			System.out.println("\nDropping the collection");

			empsCollection.drop();

		};
	}
}