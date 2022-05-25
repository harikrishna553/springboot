package com.sample.app.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigController {

	@Autowired
	private Environment environment;

	@RequestMapping("/app-configs")
	public ResponseEntity<Map<String, String>> appConfigs() {

		String prop1 = environment.getProperty("prop1");
		String prop2 = environment.getProperty("prop2");
		String globalProp1 = environment.getProperty("globalProp1");

		Map<String, String> appProperties = new HashMap<>();
		appProperties.put("prop1", prop1);
		appProperties.put("prop2", prop2);
		appProperties.put("globalProp1", globalProp1);

		return ResponseEntity.ok(appProperties);

	}
}
