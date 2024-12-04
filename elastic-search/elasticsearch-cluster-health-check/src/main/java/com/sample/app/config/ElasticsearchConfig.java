package com.sample.app.config;

import java.time.Duration;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.TrustAllStrategy;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.ssl.SSLContextBuilder;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class ElasticsearchConfig {

	private static final SSLContext TRUST_ALL_SSL_CONTEXT = createTrustAllSSLContext();

	private static SSLContext createTrustAllSSLContext() {
		try {
			return SSLContextBuilder.create().loadTrustMaterial(new TrustAllStrategy()).build();
		} catch (Exception e) {
			throw new RuntimeException("Failed to create SSL context with trust all strategy", e);
		}
	}

	@Bean
	RestClient restClient() {

		HttpHost httpHost = new HttpHost("localhost", 9200, "https");
		HttpHost[] httpHosts = { httpHost };
		String username = "elastic";
		String password = "66HhnIDJkHSV-mt16x1j";

		CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
		credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(username, password));

		RestClientBuilder builder = RestClient.builder(httpHosts);

		builder.setHttpClientConfigCallback(httpClientBuilder -> httpClientBuilder
				.setDefaultCredentialsProvider(credentialsProvider).setSSLContext(TRUST_ALL_SSL_CONTEXT)
				.setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE).setMaxConnTotal(100).setMaxConnPerRoute(20));

		builder.setRequestConfigCallback(requestConfigBuilder -> requestConfigBuilder
				.setConnectionRequestTimeout((int) Duration.ofSeconds(5000).toMillis())
				.setConnectTimeout((int) Duration.ofSeconds(5000).toMillis())
				.setSocketTimeout((int) Duration.ofSeconds(5000).toMillis()));

		RestClient restClient = builder.build();

		return restClient;

	}

	@Bean
	ObjectMapper objectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		// Configure your desired Jackson features here (e.g., serialization format,
		// custom serializers/deserializers)
		return mapper;
	}

}
