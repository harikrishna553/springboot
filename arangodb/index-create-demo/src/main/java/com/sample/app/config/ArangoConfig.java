package com.sample.app.config;

import org.springframework.context.annotation.Configuration;

import com.arangodb.ArangoDB;
import com.arangodb.springframework.annotation.EnableArangoRepositories;
import com.arangodb.springframework.config.ArangoConfiguration;

@Configuration
@EnableArangoRepositories(basePackages = { "com.sample.app" })
public class ArangoConfig implements ArangoConfiguration {

	@Override
	public ArangoDB.Builder arango() {
		return new ArangoDB.Builder().host("localhost", 8529).user("root").password("tiger");
	}

	@Override
	public String database() {
		return "abc_org";
	}
}