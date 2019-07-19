package com.sample.app.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sample.app.filter.PerformanceFilter;

@Configuration
public class FilterConfig {
	@Bean
	public FilterRegistrationBean<PerformanceFilter> perfFilter() {
		FilterRegistrationBean<PerformanceFilter> registration = new FilterRegistrationBean<>();
		registration.setFilter(new PerformanceFilter());
		registration.addUrlPatterns("/*");
		return registration;
	}
}


