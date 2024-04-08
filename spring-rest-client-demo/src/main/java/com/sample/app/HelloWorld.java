package com.sample.app;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClient.ResponseSpec;

public class HelloWorld {

	public static void main(String[] args) throws URISyntaxException {
		RestClient restClient = RestClient.create();

		ResponseSpec responseSpec = restClient
				.get()
				.uri(new URI("http://localhost:8080/api/v1/employees"))
				.header("accept", "application/json")
				.retrieve();
		List<Map<String, Object>> emps = responseSpec.body(List.class);

		for (Map<String, Object> emp : emps) {
			System.out.println(emp);
		}

	}

}