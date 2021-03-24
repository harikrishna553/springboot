package com.sample.app.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

import com.sample.app.filter.LoggingFilter;

@Configuration
public class AppConfig {
	@Bean
	public FilterRegistrationBean<LoggingFilter> perfFilter() {
		FilterRegistrationBean<LoggingFilter> registration = new FilterRegistrationBean<>();
		registration.setFilter(new LoggingFilter());
		registration.addUrlPatterns("/*");
		return registration;
	}

	@Bean
	public FilterRegistrationBean<ShallowEtagHeaderFilter> shallowEtagHeaderFilter() {
		FilterRegistrationBean<ShallowEtagHeaderFilter> filterRegistrationBean = new FilterRegistrationBean<>(
				new ShallowEtagHeaderFilter());
		filterRegistrationBean.addUrlPatterns("/*");
		filterRegistrationBean.setName("etagFilter");
		return filterRegistrationBean;
	}
}

