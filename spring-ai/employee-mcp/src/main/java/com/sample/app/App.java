package com.sample.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point for the Employee MCP Server.
 *
 * <p>
 * This application demonstrates how to build a complete MCP (Model Context
 * Protocol) server using Spring AI. It exposes employee management capabilities
 * through three MCP primitives:
 *
 * <ul>
 * <li><b>Tools</b> — 15 callable functions (e.g., getEmployees,
 * searchEmployees)</li>
 * <li><b>Resources</b> — 15 static data endpoints (e.g., employees://all)</li>
 * <li><b>Prompts</b> — 6 reusable prompt templates (e.g.,
 * employee-summary)</li>
 * </ul>
 *
 * <p>
 * Transport: Stateless HTTP (POST /mcp). No SSE sessions required.
 *
 * <p>
 * Data: ~100 in-memory employees initialized at startup. No database needed.
 *
 * @see com.sample.app.tools.EmployeeTools
 * @see com.sample.app.resources.EmployeeResourceRegistrar
 * @see com.sample.app.prompts.EmployeePromptRegistrar
 */
@SpringBootApplication
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
