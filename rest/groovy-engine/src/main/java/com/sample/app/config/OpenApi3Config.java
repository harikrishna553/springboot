package com.sample.app.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Groovy service", version = "v1"), servers = {
		@Server(url = "http://localhost:8080", description = "local server") })
public class OpenApi3Config {

}