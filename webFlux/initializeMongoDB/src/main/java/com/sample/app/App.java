package com.sample.app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.sample.app.entity.Employee;
import com.sample.app.repository.EmployeeRepository;

import reactor.core.publisher.Flux;

@SpringBootApplication
public class App {
	public static void main(String[] args) {

		SpringApplication.run(App.class, args);
	}

	@Bean
	public CommandLineRunner demo(EmployeeRepository empRepository) {
		return (args) -> {
			Employee emp1 = new Employee("Phalgun", "Garimella");
			Employee emp2 = new Employee("Sankalp", "Dubey");
			Employee emp3 = new Employee("Arpan", "Debroy");

			Flux<Employee> empsFlux = Flux.just(emp1, emp2, emp3);

			empsFlux.map(emp -> empRepository.save(emp))
					.subscribe(result -> System.out.println("Created employee : " + result.block()));

			System.out.println("\nGetting all the employees from database\n");
			empRepository.findAll().subscribe(System.out::println);

		};
	}
}
