package com.sample.app.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	private String getUrlWithQueryParms(final HttpServletRequest request) {
		if(request.getQueryString() == null) {
			return request.getRequestURL().toString();
		}
		return request.getRequestURL().append("?").append(request.getQueryString()).toString();
	}

	@GetMapping("/")
	public String home(HttpServletRequest request) {

		String result = getUrlWithQueryParms(request);
		
		return result;
	}

}