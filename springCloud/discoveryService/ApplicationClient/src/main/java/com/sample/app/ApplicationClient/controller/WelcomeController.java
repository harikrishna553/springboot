package com.sample.app.ApplicationClient.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

@RestController
public class WelcomeController {

	@Autowired
	private EurekaClient eurekaClient;

	@Autowired
	DiscoveryClient discoveryClient;

	@RequestMapping("/")
	public String home() {
		return "Welcome to spring boot developement";
	}

	@RequestMapping("/eureka/service")
	public String serviceURL() {
		InstanceInfo instanceInfo = eurekaClient.getNextServerFromEureka("welcome_service", false);
		String homePageURL = instanceInfo.getHomePageUrl();
		return homePageURL;
	}

	@RequestMapping("/discovery/service")
	public String serviceUrls() {
		List<ServiceInstance> instances = discoveryClient.getInstances("welcome_service");

		StringBuilder builder = new StringBuilder();

		for (ServiceInstance instance : instances) {
			builder.append(instance.getUri().toString()).append("<br />");
		}
		return builder.toString();
	}
}
