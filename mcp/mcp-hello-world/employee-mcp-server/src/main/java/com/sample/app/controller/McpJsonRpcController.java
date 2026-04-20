package com.sample.app.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.app.mcp.EmployeePromptProvider;
import com.sample.app.mcp.EmployeePromptProvider.McpResponse;
import com.sample.app.mcp.EmployeePromptProvider.Prompt;
import com.sample.app.mcp.EmployeeResourceProvider;
import com.sample.app.mcp.EmployeeResourceProvider.Resource;
import com.sample.app.mcp.EmployeeResourceProvider.ResourceContent;
import com.sample.app.mcp.EmployeeToolProvider;
import com.sample.app.mcp.EmployeeToolProvider.Tool;
import com.sample.app.mcp.EmployeeToolProvider.ToolResponse;

/**
 * MCP JSON-RPC 2.0 Server Controller
 *
 * Implements the Model Context Protocol (MCP) using JSON-RPC 2.0 transport.
 * Exposes MCP endpoints at /mcp with support for: - initialize - prompts/list -
 * prompts/get - resources/list - resources/read - tools/list - tools/call
 */
@RestController
@RequestMapping("/mcp")
public class McpJsonRpcController {

	private static final Logger logger = LoggerFactory.getLogger(McpJsonRpcController.class);
	private static final ObjectMapper objectMapper = new ObjectMapper();

	@Autowired
	private EmployeePromptProvider promptProvider;

	@Autowired
	private EmployeeResourceProvider resourceProvider;

	@Autowired
	private EmployeeToolProvider toolProvider;

	/**
	 * Main JSON-RPC 2.0 endpoint for MCP All MCP requests go through this single
	 * endpoint
	 */
	@PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> handleJsonRpcRequest(@RequestBody Map<String, Object> request) {
		logger.debug("Received MCP JSON-RPC request: {}", request);

		try {
			String jsonrpc = (String) request.get("jsonrpc");
			String method = (String) request.get("method");
			Object id = request.get("id");
			Map<String, Object> params = (Map<String, Object>) request.get("params");

			// Validate JSON-RPC 2.0 format
			if (!"2.0".equals(jsonrpc) || method == null) {
				return ResponseEntity.badRequest().body(createErrorResponse(id, -32600, "Invalid Request"));
			}

			if (params == null) {
				params = new HashMap<>();
			}

			// Route to appropriate handler
			Map<String, Object> result = handleMcpMethod(method, params);

			// Build JSON-RPC 2.0 response
			Map<String, Object> response = new HashMap<>();
			response.put("jsonrpc", "2.0");
			response.put("result", result);
			if (id != null) {
				response.put("id", id);
			}

			logger.debug("MCP JSON-RPC response: {}", response);
			return ResponseEntity.ok(response);

		} catch (Exception e) {
			logger.error("Error processing MCP request", e);
			return ResponseEntity
					.ok(createErrorResponse(request.get("id"), -32603, "Internal error: " + e.getMessage()));
		}
	}

	/**
	 * Route to appropriate MCP method handler
	 */
	private Map<String, Object> handleMcpMethod(String method, Map<String, Object> params) {
		return switch (method) {
		case "initialize" -> handleInitialize();
		case "prompts/list" -> handleListPrompts();
		case "prompts/get" -> handleGetPrompt(params);
		case "resources/list" -> handleListResources();
		case "resources/read" -> handleReadResource(params);
		case "tools/list" -> handleListTools();
		case "tools/call" -> handleCallTool(params);
		case "notifications/initialized" -> handleNotificationInitialized(params);
		case "resources/templates/list" -> handleListResourceTemplates();
		default -> throw new IllegalArgumentException("Unknown method: " + method);
		};
	}

	private Map<String, Object> handleListResourceTemplates() {
		List<EmployeeResourceProvider.ResourceTemplate> templates = resourceProvider.templates();

		List<Map<String, Object>> templateList = new ArrayList<>();

		for (EmployeeResourceProvider.ResourceTemplate template : templates) {

			Map<String, Object> templateMap = new HashMap<>();
			templateMap.put("uriTemplate", template.getUriTemplate());
			templateMap.put("name", template.getName());
			templateMap.put("title", template.getTitle());
			templateMap.put("description", template.getDescription());
			templateMap.put("mimeType", template.getMimeType());

			List<Map<String, Object>> icons = new ArrayList<>();
			for (EmployeeResourceProvider.Icon icon : template.getIcons()) {
				Map<String, Object> iconMap = new HashMap<>();
				iconMap.put("src", icon.getSrc());
				iconMap.put("mimeType", icon.getMimeType());
				iconMap.put("sizes", icon.getSizes());
				icons.add(iconMap);
			}

			templateMap.put("icons", icons);

			templateList.add(templateMap);
		}

		Map<String, Object> result = new HashMap<>();
		result.put("resourceTemplates", templateList);

		return result;
	}

	private Map<String, Object> handleNotificationInitialized(Map<String, Object> params) {
		logger.info(params.toString());
		Map<String, Object> map = new HashMap<>();
		map.put("initialzied", true);
		return map;
	}

	/**
	 * MCP: Initialize Server capabilities and version negotiation
	 */
	private Map<String, Object> handleInitialize() {
		Map<String, Object> result = new HashMap<>();

		// Server info
		Map<String, String> serverInfo = new HashMap<>();
		serverInfo.put("name", "employee-mcp-server");
		serverInfo.put("version", "0.0.1");
		result.put("serverInfo", serverInfo);

		// Protocol version
		result.put("protocolVersion", "2024-11-05");

		// Server capabilities
		Map<String, Map<String, Object>> capabilities = new HashMap<>();

		Map<String, Object> promptsCapability = new HashMap<>();
		promptsCapability.put("listChanged", true);
		capabilities.put("prompts", promptsCapability);

		Map<String, Object> resourcesCapability = new HashMap<>();
		resourcesCapability.put("subscribe", false);
		resourcesCapability.put("listChanged", true);
		capabilities.put("resources", resourcesCapability);

		Map<String, Object> toolsCapability = new HashMap<>();
		toolsCapability.put("listChanged", true);
		capabilities.put("tools", toolsCapability);

		result.put("capabilities", capabilities);

		return result;
	}

	/**
	 * MCP: List Prompts Returns all available prompts
	 */
	private Map<String, Object> handleListPrompts() {
		List<Prompt> prompts = promptProvider.prompts();
		List<Map<String, Object>> promptList = new ArrayList<>();

		for (Prompt prompt : prompts) {
			Map<String, Object> promptMap = new HashMap<>();
			promptMap.put("name", prompt.getName());
			promptMap.put("description", prompt.getDescription());

			// Convert arguments to MCP format
			List<Map<String, Object>> arguments = new ArrayList<>();
			for (EmployeePromptProvider.PromptArgument arg : prompt.getArguments()) {
				Map<String, Object> argMap = new HashMap<>();
				argMap.put("name", arg.getName());
				argMap.put("description", arg.getDescription());
				argMap.put("required", arg.isRequired());
				arguments.add(argMap);
			}
			promptMap.put("arguments", arguments);

			promptList.add(promptMap);
		}

		Map<String, Object> result = new HashMap<>();
		result.put("prompts", promptList);
		return result;
	}

	private Map<String, Object> handleGetPrompt(Map<String, Object> params) {
		String name = (String) params.get("name");

		@SuppressWarnings("unchecked")
		Map<String, String> arguments = (Map<String, String>) params.get("arguments");

		if (name == null) {
			throw new IllegalArgumentException("Missing 'name' parameter");
		}

		if (arguments == null) {
			arguments = new HashMap<>();
		}

		// Get MCP-compliant response
		McpResponse response = promptProvider.getMcpPrompt(name, arguments);

		// Convert McpResponse to Map<String, Object> for JSON serialization
		Map<String, Object> result = new HashMap<>();
		result.put("description", response.getDescription());

		List<Map<String, Object>> messages = response.getMessages().stream().map(msg -> Map.of("role", msg.getRole(),
				"content", Map.of("type", msg.getContent().getType(), "text", msg.getContent().getText()))).toList();

		result.put("messages", messages);

		return result;
	}

	/**
	 * MCP: List Resources Returns all available resources
	 */
	private Map<String, Object> handleListResources() {
		List<Resource> resources = resourceProvider.resources();
		List<Map<String, Object>> resourceList = new ArrayList<>();

		for (Resource resource : resources) {
			Map<String, Object> resMap = new HashMap<>();
			resMap.put("uri", resource.getUri());
			resMap.put("name", resource.getName());
			resMap.put("description", resource.getDescription());
			resMap.put("mimeType", resource.getMimeType());
			resourceList.add(resMap);
		}

		Map<String, Object> result = new HashMap<>();
		result.put("resources", resourceList);
		return result;
	}

	/**
	 * MCP: Read Resource Returns content of a specific resource
	 */
	private Map<String, Object> handleReadResource(Map<String, Object> params) {
		String uri = (String) params.get("uri");

		if (uri == null) {
			throw new IllegalArgumentException("Missing 'uri' parameter");
		}

		ResourceContent content = resourceProvider.getResource(uri);

		Map<String, Object> result = new HashMap<>();
		result.put("contents", List.of(Map.of("uri", uri, "mimeType", "text/plain", "text", content.getText())));

		return result;
	}

	/**
	 * MCP: List Tools Returns all available tools with their schemas
	 */
	private Map<String, Object> handleListTools() {
		List<Tool> tools = toolProvider.tools();
		List<Map<String, Object>> toolList = new ArrayList<>();

		for (Tool tool : tools) {
			Map<String, Object> toolMap = new HashMap<>();
			toolMap.put("name", tool.getName());
			toolMap.put("description", tool.getDescription());
			toolMap.put("inputSchema", tool.getInputSchema());
			toolList.add(toolMap);
		}

		Map<String, Object> result = new HashMap<>();
		result.put("tools", toolList);
		return result;
	}

	/**
	 * MCP: Call Tool Executes a tool with provided arguments
	 */
	private Map<String, Object> handleCallTool(Map<String, Object> params) {
		String name = (String) params.get("name");
		@SuppressWarnings("unchecked")
		Map<String, Object> arguments = (Map<String, Object>) params.get("arguments");

		if (name == null) {
			throw new IllegalArgumentException("Missing 'name' parameter");
		}

		if (arguments == null) {
			arguments = new HashMap<>();
		}

		ToolResponse response = toolProvider.call(name, arguments);

		Map<String, Object> result = new HashMap<>();
		result.put("content", List.of(Map.of("type", "text", "text", response.getText())));

		if (response.isError()) {
			result.put("isError", true);
		}

		return result;
	}

	/**
	 * Create a JSON-RPC 2.0 error response
	 */
	private Map<String, Object> createErrorResponse(Object id, int code, String message) {
		Map<String, Object> error = new HashMap<>();
		error.put("code", code);
		error.put("message", message);

		Map<String, Object> response = new HashMap<>();
		response.put("jsonrpc", "2.0");
		response.put("error", error);
		if (id != null) {
			response.put("id", id);
		}

		return response;
	}

}
