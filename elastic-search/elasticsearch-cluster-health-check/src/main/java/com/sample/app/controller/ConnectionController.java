package com.sample.app.controller;

import java.io.IOException;
import java.util.Map;

import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

/**
 * Controller for managing Elasticsearch connections and retrieving cluster
 * health information.
 * 
 * This controller exposes an endpoint to check the health of the connected
 * Elasticsearch cluster using the {@link RestClient}.
 */
@RestController
@RequestMapping("/api/connections")
public class ConnectionController {

	@Autowired
	private RestClient restClient;

	@Autowired
	private ObjectMapper objectMapper;

	/**
	 * Retrieves the health status of the connected Elasticsearch cluster.
	 *
	 * @return A map containing the cluster's health information.
	 * @throws IOException If an error occurs while communicating with
	 *                     Elasticsearch.
	 * 
	 *                     ### Example Response: ```json { "name": "node-1",
	 *                     "cluster_name": "elasticsearch", "cluster_uuid":
	 *                     "abc123", "version": { "number": "8.x.x", "build_flavor":
	 *                     "default", "build_type": "docker", "build_hash": "xyz",
	 *                     "build_date": "2024-01-01T00:00:00Z", "lucene_version":
	 *                     "9.x.x" }, "tagline": "You Know, for Search" } ```
	 */
	@Operation(summary = "Check Elasticsearch cluster health", description = "Retrieves the health information of the connected Elasticsearch cluster, including its name, version, and status.", responses = {
			@ApiResponse(responseCode = "200", description = "Successfully retrieved cluster health information", content = @Content(mediaType = "application/json", examples = @ExampleObject(name = "Sample Cluster Health Response", value = "{\n"
					+ "  \"name\": \"node-1\",\n" + "  \"cluster_name\": \"elasticsearch\",\n"
					+ "  \"cluster_uuid\": \"abc123\",\n" + "  \"version\": {\n" + "    \"number\": \"8.x.x\",\n"
					+ "    \"build_flavor\": \"default\",\n" + "    \"build_type\": \"docker\",\n"
					+ "    \"lucene_version\": \"9.x.x\"\n" + "  },\n" + "  \"tagline\": \"You Know, for Search\"\n"
					+ "}"))),
			@ApiResponse(responseCode = "500", description = "Failed to retrieve cluster health information", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Map.class))) })
	@GetMapping(value = "/health")
	public Map<String, Object> health() throws IOException {
		Request request = new Request("GET", "/");
		Response response = restClient.performRequest(request);
		String responseBody = EntityUtils.toString(response.getEntity());
		return toMap(responseBody);
	}

	/**
	 * Converts a JSON string into a {@link Map}.
	 *
	 * @param json The JSON string to convert.
	 * @return A map representation of the JSON string.
	 * @throws JsonMappingException    If there is an error mapping the JSON string
	 *                                 to a map.
	 * @throws JsonProcessingException If there is an error processing the JSON
	 *                                 string.
	 */
	private Map<String, Object> toMap(String json) throws JsonMappingException, JsonProcessingException {
		return objectMapper.readValue(json, new TypeReference<Map<String, Object>>() {
		});
	}
}
