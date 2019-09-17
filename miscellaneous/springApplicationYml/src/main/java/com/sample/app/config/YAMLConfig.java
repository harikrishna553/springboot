package com.sample.app.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
public class YAMLConfig {

	private String name;
	private String environment;
	private String projectTechName;
	private List<String> servers = new ArrayList<>();
	private int noOfDevelopers;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}

	public List<String> getServers() {
		return servers;
	}

	public void setServers(List<String> servers) {
		this.servers = servers;
	}

	public String getProjectTechName() {
		return projectTechName;
	}

	public void setProjectTechName(String projectTechName) {
		this.projectTechName = projectTechName;
	}

	public int getNoOfDevelopers() {
		return noOfDevelopers;
	}

	public void setNoOfDevelopers(int noOfDevelopers) {
		this.noOfDevelopers = noOfDevelopers;
	}

}
