package com.sample.app;

import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.sample.app.model.Employee;
import com.sample.app.service.EmployeeRestClientService;

public class App {

	public static void main(String args[]) {
		String baseURI = "http://localhost:8080/";
		WebClient webClient = WebClient.create(baseURI);

		EmployeeRestClientService empService = new EmployeeRestClientService(webClient);

		System.out.println("Retrieving employee with id 2");
		Employee emp = empService.byId(2);
		System.out.println(emp);

		System.out.println("Deleting employee with id 2");
		emp = empService.deleteEmployee(2);
		System.out.println(emp);

		try {
			System.out.println("Retrieving employee with id 2");
			emp = empService.byId(2);
			System.out.println(emp);

		} catch (WebClientResponseException e) {
			System.out.println(e.getMessage());
		}

	}

}
