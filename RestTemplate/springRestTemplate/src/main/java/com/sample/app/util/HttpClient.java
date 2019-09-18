package com.sample.app.util;

import java.util.Map;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

public class HttpClient {

	private static final RestTemplate REST_TEMPLATE = new RestTemplate(getClientHttpRequestFactory());
	
	private static ClientHttpRequestFactory getClientHttpRequestFactory() {
	    int timeout = 5000;
	    RequestConfig config = RequestConfig.custom()
	      .setConnectTimeout(timeout)
	      .setConnectionRequestTimeout(timeout)
	      .setSocketTimeout(timeout)
	      .build();
	    CloseableHttpClient client = HttpClientBuilder
	      .create()
	      .setDefaultRequestConfig(config)
	      .build();
	    return new HttpComponentsClientHttpRequestFactory(client);
	}

	private static HttpHeaders getHeadersFromMap(Map<String, String> headers) {
		HttpHeaders httpHeaders = new HttpHeaders();

		if (headers == null || headers.isEmpty()) {
			return httpHeaders;
		}

		for (Map.Entry<String, String> entry : headers.entrySet()) {
			httpHeaders.set(entry.getKey(), entry.getValue());
		}

		return httpHeaders;
	}

	public static ResponseEntity<String> get(String url) {
		return get(url, null);
	}

	public static ResponseEntity<String> get(String url, Map<String, String> headers) {
		if (url == null || url.isEmpty()) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("URL must not be null");
		}

		HttpEntity<Object> httpEntity = new HttpEntity<>(headers);

		return REST_TEMPLATE.exchange(url, HttpMethod.GET, httpEntity, String.class);

	}

	public static ResponseEntity<String> put(String url, String json) {
		return put(url, null, json);
	}

	public static ResponseEntity<String> put(String url, Map<String, String> headers, String json) {
		if (url == null || url.isEmpty()) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("URL must not be null");
		}

		HttpHeaders httpHeaders = getHeadersFromMap(headers);

		HttpEntity<Object> httpEntity = new HttpEntity<Object>(json, httpHeaders);

		return REST_TEMPLATE.exchange(url, HttpMethod.PUT, httpEntity, String.class);

	}

	public static ResponseEntity<String> post(String url, String json) {
		return put(url, null, json);
	}

	public static ResponseEntity<String> post(String url, Map<String, String> headers, String json) {
		if (url == null || url.isEmpty()) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("URL must not be null");
		}

		HttpHeaders httpHeaders = getHeadersFromMap(headers);

		HttpEntity<Object> httpEntity = new HttpEntity<Object>(json, httpHeaders);

		return REST_TEMPLATE.exchange(url, HttpMethod.POST, httpEntity, String.class);

	}

	public static ResponseEntity<String> delete(String url) {
		return delete(url, null);
	}

	public static ResponseEntity<String> delete(String url, Map<String, String> headers) {
		if (url == null || url.isEmpty()) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("URL must not be null or empty");
		}

		HttpHeaders httpHeaders = getHeadersFromMap(headers);

		HttpEntity<Object> httpEntity = new HttpEntity<Object>(httpHeaders);

		return REST_TEMPLATE.exchange(url, HttpMethod.DELETE, httpEntity, String.class);

	}

	public static <T> T getResponse(String url, Class<T> clazz) {
		if (url == null || url.isEmpty()) {
			throw new RuntimeException("url must not be null or empty");
		}

		return REST_TEMPLATE.getForObject(url, clazz);
	}

	public static HttpHeaders getHeaders(String url) {
		if (url == null || url.isEmpty()) {
			throw new RuntimeException("url must not be null or empty");
		}

		return REST_TEMPLATE.headForHeaders(url);
	}

}
