package com.sample.app.comntroller;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sample.app.entity.Employee;
import com.sample.app.events.EmployeeEvent;
import com.sample.app.repository.EmployeeRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1/")
public class EmployeeController {

	@Autowired
	private EmployeeRepository empRepository;

	@RequestMapping(value = "employees", method = RequestMethod.GET)
	public Flux<Employee> all() {
		return empRepository.findAll();
	}

	@RequestMapping(value = "employees", method = RequestMethod.DELETE)
	public Mono<Void> deleteAll() {
		return empRepository.deleteAll();
	}

	@RequestMapping(value = "employees/{id}", method = RequestMethod.GET)
	public Mono<ResponseEntity<Employee>> byId(@PathVariable("id") String id) {
		return empRepository.findById(id).map(emp -> ResponseEntity.ok(emp))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}

	@RequestMapping(value = "employees/{id}", method = RequestMethod.PUT)
	public Mono<ResponseEntity<Employee>> update(@PathVariable("id") String id, @RequestBody Employee emp) {

		return empRepository.findById(id).flatMap(persistedEmp -> {
			persistedEmp.setFirstName(emp.getFirstName());
			persistedEmp.setLastName(emp.getLastName());
			return empRepository.save(persistedEmp);
		}).map(persistedEmp -> ResponseEntity.status(HttpStatus.CREATED).body(persistedEmp))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}

	@RequestMapping(value = "employees/{id}", method = RequestMethod.DELETE)
	public Mono<ResponseEntity<Void>> delete(@PathVariable("id") String id) {

		return empRepository.findById(id)
				.flatMap(persistedEmployee -> empRepository.delete(persistedEmployee)
						.then(Mono.just(ResponseEntity.ok().<Void>build())))
				.defaultIfEmpty(ResponseEntity.notFound().build());

	}

	@RequestMapping(value = "employees", method = RequestMethod.POST)
	public Mono<ResponseEntity<Employee>> create(@RequestBody Employee emp) {

		return empRepository.save(emp)
				.map(persistedEmp -> ResponseEntity.status(HttpStatus.CREATED).body(persistedEmp));
	}

	@RequestMapping(value = "employees/events", method = RequestMethod.GET, produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<EmployeeEvent> getEmployeeEvents() {
		return Flux.interval(Duration.ofSeconds(1)).map(val -> new EmployeeEvent(val, "Employee Event"));

	}
}
