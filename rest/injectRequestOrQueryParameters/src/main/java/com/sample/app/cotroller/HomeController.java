package com.sample.app.cotroller;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@RequestMapping("/")
	public String home(@RequestParam(name = "name", defaultValue = "user") String userName, @RequestParam(name = "gender", defaultValue = "m") String gender) {
		StringBuilder builder = new StringBuilder();
		
		builder.append("Hello, ");
		
		if("f".equalsIgnoreCase(gender)) {
			builder.append(" Madam ");
		}else {
			builder.append("Mr. ");
		}
		
		builder.append(userName).append(". Have a great day!!!!!!");
		
		return builder.toString();
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

	@RequestMapping("/userAgent")
	public ResponseEntity<String> getData(@RequestHeader(value = "user-agent") String userAgent) {
		return ResponseEntity.ok(userAgent);

	}

}
