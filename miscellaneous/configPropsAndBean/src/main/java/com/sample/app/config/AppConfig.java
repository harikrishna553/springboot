package com.sample.app.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sample.app.model.UserDetails;

@Configuration
public class AppConfig {

	@Bean
	@ConfigurationProperties(prefix = "app.datasource")
	@Qualifier("myAppDetails")
	public UserDetails appDetails() {
		return new UserDetails();
	}

	@Bean
	@ConfigurationProperties(prefix = "hive.datasource")
	@Qualifier("myHiveDetails")
	public UserDetails hiveDetails() {
		return new UserDetails();
	}

}
