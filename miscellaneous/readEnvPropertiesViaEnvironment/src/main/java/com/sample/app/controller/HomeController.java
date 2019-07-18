package com.sample.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.app.model.ConfigProperties;

@RestController
public class HomeController {

	@Autowired
	ConfigProperties configProp;

	@RequestMapping("/")
	public String homePage() {
		return "Welcome to Spring boot Application Development";
	}

	@RequestMapping("/config")
	public String configValues() {
		String appName = configProp.getConfigValue("app.name");
		String appVersion = configProp.getConfigValue("app.version");

		StringBuilder builder = new StringBuilder();
		builder.append("Application Name : ").append(appName).append(", Application Version : ").append(appVersion);

		return builder.toString();
	}

}