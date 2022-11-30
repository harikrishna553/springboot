package com.sample.app.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import com.sample.app.rs.impl.HelloEndpointImpl;

@Configuration
public class JerseyConfig extends ResourceConfig {
	public JerseyConfig() {
		register(HelloEndpointImpl.class);
	}
}