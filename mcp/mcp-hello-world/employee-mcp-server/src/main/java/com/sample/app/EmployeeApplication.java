package com.sample.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Employee MCP HTTP Server Application
 *
 * A production-grade MCP (Model Context Protocol) HTTP server that exposes
 * employee-related prompts, resources, and tools via the MCP specification.
 *
 * The server is accessible at: http://localhost:8080/mcp
 *
 * Supports MCP primitives:
 * - listPrompts() - Returns available prompts
 * - listResources() - Returns available resources
 * - listTools() - Returns available tools
 */
@SpringBootApplication
public class EmployeeApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeApplication.class, args);
	}

}
