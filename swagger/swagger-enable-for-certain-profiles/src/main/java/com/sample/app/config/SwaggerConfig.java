package com.sample.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Profile({"dev","qa"})
public class SwaggerConfig {

	@Bean
	public Docket docketApi() {

		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select().paths(PathSelectors.any()).build();
	}

	private ApiInfo apiInfo() {

		return new ApiInfoBuilder()
				.title("Spring boot swagger demo application")
				.description("REST APIs for spring boot service")
				.license("Apache License Version 2.0")
				.licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
				.version("2.0")
				.contact(new Contact("chat server dev team details", "https://demochatserver.demo.com", "chatserver@demo.com"))
				.build();
	}
}