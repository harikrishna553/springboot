package com.sample.app;

import java.net.URI;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;

import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.config.ConnectionConfig;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.client5.http.io.HttpClientConnectionManager;
import org.apache.hc.client5.http.ssl.NoopHostnameVerifier;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactory;
import org.apache.hc.core5.pool.PoolConcurrencyPolicy;
import org.apache.hc.core5.pool.PoolReusePolicy;
import org.apache.hc.core5.ssl.SSLContexts;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClient.ResponseSpec;

public class RestClientBuilderDemo {

	private static HttpClientConnectionManager getConnectionManager() throws Exception {

		// setting up an SSLContext with custom trust management, where it will trust
		// any certificate presented by the server
		SSLContext sslContext = SSLContexts.custom()
				.loadTrustMaterial((X509Certificate[] chain, String authType) -> true).build();

		final SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext,
				NoopHostnameVerifier.INSTANCE);

		ConnectionConfig connectionConfig = ConnectionConfig.custom().setConnectTimeout(10, TimeUnit.SECONDS)
				.setSocketTimeout(10, TimeUnit.SECONDS).setTimeToLive(180, TimeUnit.SECONDS)
				.setValidateAfterInactivity(30, TimeUnit.SECONDS).build();

		PoolingHttpClientConnectionManager manager = PoolingHttpClientConnectionManagerBuilder.create()
				.setPoolConcurrencyPolicy(PoolConcurrencyPolicy.LAX).setConnPoolPolicy(PoolReusePolicy.LIFO)
				.setDefaultConnectionConfig(connectionConfig).setSSLSocketFactory(sslsf).setMaxConnTotal(500)
				.setMaxConnPerRoute(30).build();

		return manager;

	}

	private static HttpComponentsClientHttpRequestFactory factory() throws Exception {
		// Create HttpClient
		HttpClient httpClient = HttpClientBuilder.create().setConnectionManager(getConnectionManager())
				.setDefaultRequestConfig(
						RequestConfig.custom().setConnectionRequestTimeout(10, TimeUnit.SECONDS).build())
				.build();

		// Create HttpComponentsClientHttpRequestFactory
		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);

		return factory;
	}

	public static void main(String[] args) throws Exception {
		RestClient restClient = RestClient.builder().requestFactory(factory()).build();

		ResponseSpec responseSpec = restClient.get().uri(new URI("http://localhost:8080/api/v1/employees"))
				.header("accept", "application/json").retrieve();
		List<Map<String, Object>> emps = responseSpec.body(List.class);

		for (Map<String, Object> emp : emps) {
			System.out.println(emp);
		}

	}

}
