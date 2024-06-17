package com.sample.app.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
public class DBConfig {
	@Bean
	public DataSource dataSource() throws IOException {
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setDriverClassName("org.h2.Driver");
		hikariConfig.setJdbcUrl("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=false");
		hikariConfig.setUsername("sa");
		hikariConfig.setPassword("");
		hikariConfig.setMaximumPoolSize(10);
		hikariConfig.setIdleTimeout(600000); // 10 minutes
		hikariConfig.setConnectionTimeout(30000); // 30 seconds
		hikariConfig.setMaxLifetime(1800000); // 30 minutes
		hikariConfig.setConnectionTestQuery("SELECT 1");
		hikariConfig.setValidationTimeout(5000); // 5 seconds
		hikariConfig.setAutoCommit(true);
		hikariConfig.setPoolName("springHikariCP");

		return new HikariDataSource(hikariConfig);
	}
}
