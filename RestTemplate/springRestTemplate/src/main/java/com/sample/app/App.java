package com.sample.app;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import com.sample.app.model.Employee;
import com.sample.app.util.HttpClient;


public class App {

	private static void printAllEmployees() {
		ResponseEntity<String> response = HttpClient.get("http://localhost:8080/api/v1/employees/");
		System.out.println(response.getBody());
	}

	public static void main(String args[]) {

		String emp1 = "{\"id\":1,\"firstName\":\"Harini\",\"lastName\":\"Gurram\"}";
		Map<String, String> headers = new HashMap<>();
		headers.put("Accept", "application/json");
		headers.put("Content-Type", "application/json");

		ResponseEntity<String> response = HttpClient.put("http://localhost:8080/api/v1/employees/1", headers, emp1);
		System.out.println(response.getBody());
		printAllEmployees();

		String emp2 = "{\"firstName\":\"Jaideep\",\"lastName\":\"Geera\"}";
		response = HttpClient.post("http://localhost:8080/api/v1/employees/", headers, emp2);
		System.out.println(response.getBody());
		printAllEmployees();
		
		response = HttpClient.delete("http://localhost:8080/api/v1/employees/3");
		System.out.println(response.getBody());
		printAllEmployees();
		
		Employee emp3 = HttpClient.getResponse("http://localhost:8080/api/v1/employees/1", Employee.class);
		System.out.println(emp3);
		
		HttpHeaders responseHeaders = HttpClient.getHeaders("http://localhost:8080/api/v1/employees/");
		System.out.println(responseHeaders);
		
	}
}
