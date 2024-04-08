package com.sample.app;

import java.net.URI;

import org.springframework.web.util.DefaultUriBuilderFactory;

public class ConstructUri {

	public static void main(String[] args) {
		URI uri = new DefaultUriBuilderFactory()
				.uriString("http://localhost:8080/api/v1/employees/by-names")
				.queryParam("firstName", "Gopi")
				.queryParam("lastName", "Gumma")
				.build();

		 System.out.println("Constructed URI: " + uri);
	}

}
