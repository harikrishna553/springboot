package com.sample.app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EnvironmentConfig {

	@Value("${app.name}")
	private String appName;

	@Value("${app.version}")
	private String appVersion;

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EnvironmentConfig [appName=").append(appName).append(", appVersion=").append(appVersion)
				.append("]");
		return builder.toString();
	}

}
