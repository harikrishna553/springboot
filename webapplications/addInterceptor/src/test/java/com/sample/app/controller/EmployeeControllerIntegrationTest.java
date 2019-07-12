package com.sample.app.controller;

import static org.junit.Assert.assertEquals;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sample.app.App;
import com.sample.app.model.Employee;

import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class EmployeeControllerIntegrationTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	int randomServerPort;

	@Test
	public void testCreateEmployee() throws URISyntaxException {
		final String baseUrl = "http://localhost:" + randomServerPort + "/api/v1/employees/";
		URI uri = new URI(baseUrl);

		HttpHeaders headers = new HttpHeaders();
		headers.set("content-type", "application/json");

		Employee emp = new Employee();
		emp.setFirstName("Lahari");
		emp.setLastName("Gurram");

		HttpEntity<Employee> request = new HttpEntity<>(emp, headers);

		ResponseEntity<Employee> result = restTemplate.postForEntity(uri, request, Employee.class);

		assertEquals(201, result.getStatusCodeValue());

	}

}


