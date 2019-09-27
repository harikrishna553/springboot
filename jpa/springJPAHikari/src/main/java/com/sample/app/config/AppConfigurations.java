package com.sample.app.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class AppConfigurations {

	@ConfigurationProperties(prefix = "spring.datasource.hikari")
	@Bean
	public DataSource dataSource() {

		HikariDataSource dataSource = new HikariDataSource();
		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setJdbcUrl("jdbc:h2:file:~/db/myOrg.db;DB_CLOSE_ON_EXIT=FALSE;DB_CLOSE_DELAY=-1;");
		dataSource.setUsername("krishna");
		dataSource.setPassword("password123");
		return dataSource;
	}
}