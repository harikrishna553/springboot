package com.sample.app.config;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.quartz.QuartzProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
public class SchedulerConfig {

	@Autowired
	private DataSource dataSource;

	@Autowired
	private QuartzProperties quartzProperties;

	@Bean
    public SchedulerFactoryBean schedulerFactoryBean() throws IOException {
 
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setOverwriteExistingJobs(true);
        factory.setDataSource(dataSource);
        
        Properties properties = new Properties();
        properties.putAll(quartzProperties.getProperties());
        
        factory.setQuartzProperties(properties);
        
        
        return factory;
    }
}