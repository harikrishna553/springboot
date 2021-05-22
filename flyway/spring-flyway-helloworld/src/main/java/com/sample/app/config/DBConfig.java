package com.sample.app.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DBConfig {
	@Bean
	public DataSource dataSource() {

		HikariDataSource dataSource = new HikariDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/abc_org");
		dataSource.setUsername("root");
		dataSource.setPassword("tiger");
		return dataSource;
	}

}
