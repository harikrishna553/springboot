package com.sample.app.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

	@Value("${name}")
	private String appName;

	@Value("${currentVersion}")
	private String currentVersion;

	@Value("#{'${supportedVersions}'.split(',')}")
	private List<String> supportedVersions;

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getCurrentVersion() {
		return currentVersion;
	}

	public void setCurrentVersion(String currentVersion) {
		this.currentVersion = currentVersion;
	}

	public List<String> getSupportedVersions() {
		return supportedVersions;
	}

	public void setSupportedVersions(List<String> supportedVersions) {
		this.supportedVersions = supportedVersions;
	}

}
