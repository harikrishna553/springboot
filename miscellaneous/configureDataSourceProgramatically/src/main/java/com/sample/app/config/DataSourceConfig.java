package com.sample.app.config;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfig {
	@Bean
	public DataSource getDataSource() {
		DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.driverClassName("org.h2.Driver");
		dataSourceBuilder.url("jdbc:h2:file:~/db/myOrg.db;DB_CLOSE_ON_EXIT=FALSE;DB_CLOSE_DELAY=-1;");
		dataSourceBuilder.username("krishna");
		dataSourceBuilder.password("password123");
		return dataSourceBuilder.build();
	}
}


