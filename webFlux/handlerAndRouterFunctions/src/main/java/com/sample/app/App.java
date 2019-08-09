package com.sample.app;

import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.sample.app.entity.Employee;
import com.sample.app.handler.EmployeeHandler;
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

	@Bean
	RouterFunction<ServerResponse> routes(EmployeeHandler employeeHandler) {
		return route(GET("/api/v2/employees").and(accept(MediaType.APPLICATION_JSON)), employeeHandler::getAllEmployees)
				.andRoute(POST("/api/v2/employees").and(accept(MediaType.APPLICATION_JSON)),
						employeeHandler::createEmployee)
				.andRoute(GET("/api/v2/employees/{id}").and(accept(MediaType.APPLICATION_JSON)),
						employeeHandler::getEmployee)
				.andRoute(PUT("/api/v2/employees/{id}").and(accept(MediaType.APPLICATION_JSON)),
						employeeHandler::updateEmployee)
				.andRoute(DELETE("/api/v2/employees").and(accept(MediaType.APPLICATION_JSON)),
						employeeHandler::deleteAllEmployees)
				.andRoute(DELETE("/api/v2/employees/{id}").and(accept(MediaType.APPLICATION_JSON)),
						employeeHandler::deleteEmployee)
				.andRoute(GET("/api/v2/employees/events").and(accept(MediaType.APPLICATION_JSON)),
						employeeHandler::employeeEvents);

	}

}

