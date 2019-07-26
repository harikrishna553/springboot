package com.sample.app.cotroller;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@RequestMapping("/")
	public String home() {

		return "Hello World";
	}

	@RequestMapping("/headers")
	public String headers(HttpServletRequest request) {

		Enumeration<String> headerNames = request.getHeaderNames();

		StringBuilder builder = new StringBuilder();

		while (headerNames.hasMoreElements()) {
			String headerName = headerNames.nextElement();
			String headerValue = request.getHeader(headerName);

			builder.append(headerName).append(" : ").append(headerValue).append("<br />");

		}

		return builder.toString();
	}

}
