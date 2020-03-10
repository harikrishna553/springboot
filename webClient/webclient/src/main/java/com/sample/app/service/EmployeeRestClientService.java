package com.sample.app.service;

import java.util.List;

import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import com.sample.app.model.Employee;

public class EmployeeRestClientService {

	private WebClient webClient;

	public EmployeeRestClientService(WebClient webClient) {
		this.webClient = webClient;
	}

	public List<Employee> emps() {

		return webClient.get().uri("api/v1/employees").retrieve().bodyToFlux(Employee.class).collectList().block();
	}

	public Employee byId(int id) {
		return webClient.get().uri("api/v1/employees/" + id).retrieve().bodyToMono(Employee.class).block();
	}

	public List<Employee> containsName(String name) {
		String uriToHit = UriComponentsBuilder.fromUriString("api/v1/employees/by-name/").queryParam("empName", name)
				.buildAndExpand().toString();

		return webClient.get().uri(uriToHit).retrieve().bodyToFlux(Employee.class).collectList().block();
	}

	public Employee addEmployee(Employee emp) {
		return webClient.post().uri("api/v1/employees").syncBody(emp).retrieve().bodyToMono(Employee.class).block();
	}

	public Employee updateEmployee(int id, Employee emp) {
		return webClient.put().uri("api/v1/employees/" + id).syncBody(emp).retrieve().bodyToMono(Employee.class)
				.block();
	}

	public Employee deleteEmployee(int id) {
		return webClient.delete().uri("api/v1/employees/" + id).retrieve().bodyToMono(Employee.class).block();
	}

}
