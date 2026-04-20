package com.sample.app;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import dev.langchain4j.agent.tool.ToolExecutionRequest;
import dev.langchain4j.agent.tool.ToolSpecification;
import dev.langchain4j.mcp.client.DefaultMcpClient;
import dev.langchain4j.mcp.client.McpGetPromptResult;
import dev.langchain4j.mcp.client.McpPromptMessage;
import dev.langchain4j.mcp.client.McpReadResourceResult;
import dev.langchain4j.mcp.client.McpResource;
import dev.langchain4j.mcp.client.McpResourceTemplate;
import dev.langchain4j.mcp.client.transport.http.StreamableHttpMcpTransport;
import dev.langchain4j.service.tool.ToolExecutionResult;

public class HelloWorld {
	public static void main(String[] args) throws JsonProcessingException {

		DefaultMcpClient client = DefaultMcpClient.builder()
				.transport(StreamableHttpMcpTransport.builder().url("http://localhost:8080/mcp") // your MCP endpoint
						.build())
				.build();

		/*
		 * McpGetPromptResult mcpPromptResult = client.getPrompt("onboarding-checklist",
		 * null); List<McpPromptMessage> mcpMessages = mcpPromptResult.messages(); for
		 * (McpPromptMessage mcpMessage : mcpMessages) { System.out.println(mcpMessage);
		 * }
		 */

		/*
		 * List<McpResource> mcpResources = client.listResources(); for(McpResource
		 * mcpResource: mcpResources) { System.out.println(mcpResource); }
		 */

		/*
		 * McpReadResourceResult resource =
		 * client.readResource("resource://employee/org-chart");
		 * System.out.println(resource);
		 */

		/*
		 * List<McpResourceTemplate> resourceTemplates = client.listResourceTemplates();
		 * for(McpResourceTemplate resourceTemplate: resourceTemplates) {
		 * System.out.println(resourceTemplate); }
		 */

		List<ToolSpecification> toolsSpecification = client.listTools();
		for (ToolSpecification toolSpec : toolsSpecification) {
			System.out.println(toolSpec);
		}
		
		ObjectMapper mapper = new ObjectMapper();

		JsonNode jsonNode = mapper.createObjectNode()
		        .put("employeeId", 123);
		
		
		ToolExecutionRequest toolExecutionRequest = ToolExecutionRequest.builder().name("get-employee-details").arguments(jsonNode.toString()).build();
		

		ToolExecutionResult toolExecutionResult = client.executeTool(toolExecutionRequest);
		System.out.println(toolExecutionResult);
	}

}
