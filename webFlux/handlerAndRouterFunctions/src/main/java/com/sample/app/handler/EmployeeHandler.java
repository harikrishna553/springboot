package com.sample.app.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.reactive.function.server.ServerRequest;

import com.sample.app.entity.Employee;
import com.sample.app.events.EmployeeEvent;
import com.sample.app.repository.EmployeeRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import static org.springframework.web.reactive.function.BodyInserters.fromObject;

import java.time.Duration;

@Component
public class EmployeeHandler {
	private static final Mono<ServerResponse> NOT_FOUND = ServerResponse.notFound().build();

	@Autowired
	private EmployeeRepository employeeRepo;

	public Mono<ServerResponse> getAllEmployees(ServerRequest serverRequest) {
		Flux<Employee> emps = employeeRepo.findAll();

		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(emps, Employee.class);
	}

	public Mono<ServerResponse> getEmployee(ServerRequest serverRequest) {
		String id = serverRequest.pathVariable("id");

		Mono<Employee> empMono = employeeRepo.findById(id);

		return empMono.flatMap(emp -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(fromObject(emp)))
				.switchIfEmpty(NOT_FOUND);

	}

	public Mono<ServerResponse> createEmployee(ServerRequest serverRequest) {
		Mono<Employee> empMono = serverRequest.bodyToMono(Employee.class);

		return empMono.flatMap(emp -> ServerResponse.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON)
				.body(employeeRepo.save(emp), Employee.class));
	}

	public Mono<ServerResponse> updateEmployee(ServerRequest serverRequest) {
		String id = serverRequest.pathVariable("id");

		Mono<Employee> persistedEmpMono = employeeRepo.findById(id);

		Mono<Employee> payLoadMono = serverRequest.bodyToMono(Employee.class);

		return payLoadMono
				.zipWith(persistedEmpMono,
						(employee, persistedEmployee) -> new Employee(persistedEmployee.getId(),
								employee.getFirstName(), employee.getLastName()))
				.flatMap(employee -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
						.body(employeeRepo.save(employee), Employee.class))
				.switchIfEmpty(NOT_FOUND);

	}

	public Mono<ServerResponse> deleteEmployee(ServerRequest serverRequest) {

		String id = serverRequest.pathVariable("id");

		Mono<Employee> persistedEmpMono = employeeRepo.findById(id);

		return persistedEmpMono.flatMap(employee -> ServerResponse.ok().build(employeeRepo.delete(employee)))
				.switchIfEmpty(NOT_FOUND);

	}

	public Mono<ServerResponse> deleteAllEmployees(ServerRequest serverRequest) {
		return ServerResponse.ok().build(employeeRepo.deleteAll());
	}

	public Mono<ServerResponse> employeeEvents(ServerRequest serverRequest) {
		Flux<EmployeeEvent> empEvents = Flux.interval(Duration.ofSeconds(1))
				.map(val -> new EmployeeEvent(val, "Employee Event"));

		return ServerResponse.ok().contentType(MediaType.TEXT_EVENT_STREAM).body(empEvents, EmployeeEvent.class);
	}
}
