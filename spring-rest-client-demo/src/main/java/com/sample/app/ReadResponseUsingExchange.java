package com.sample.app;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestClient;

public class ReadResponseUsingExchange {
	public static void main(String[] args) throws URISyntaxException {
		RestClient restClient = RestClient.create();

		List<Map<String, Object>> employees = restClient.get().uri(new URI("http://localhost:8080/api/v1/employees"))
				.header("accept", "application/json").exchange((request, response) -> {
					if (response.getStatusCode().equals(HttpStatus.OK)) {
						return response.bodyTo(List.class);
					}

					throw new RuntimeException(
							"Statuc code : " + response.getStatusCode() + ", error : " + response.getStatusText());
				});

		for (Map<String, Object> emp : employees) {
			System.out.println(emp);
		}
	}
}
