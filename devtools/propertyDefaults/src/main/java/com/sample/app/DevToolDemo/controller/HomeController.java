package com.sample.app.DevToolDemo.controller;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class HomeController {

	@Autowired
	private Environment env;

	@RequestMapping("/")
	public String homePage() {
		return "Welcome to Spring boot Application Development";
	}

	@RequestMapping("/configs")
	public Map<String, Object> configs() {
		Map<String, Object> properties = new HashMap<>();

		Iterator iterator = ((AbstractEnvironment) env).getPropertySources().iterator();

		while(iterator.hasNext()) {
			PropertySource propertySource = (PropertySource) iterator.next();
			
			if (propertySource instanceof MapPropertySource) {

				String[] propertyNames = ((MapPropertySource) propertySource).getPropertyNames();

				for (String propName : propertyNames) {
					properties.put(propName, propertySource.getProperty(propName));
				}

			}

		}
		
		return properties;
	}

}
