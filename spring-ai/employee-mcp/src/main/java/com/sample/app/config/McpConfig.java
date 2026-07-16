package com.sample.app.config;

import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sample.app.tools.EmployeeTools;

/**
 * MCP Server configuration.
 *
 * <p>
 * This class wires the three MCP primitives into the Spring AI
 * auto-configuration:
 *
 * <ol>
 * <li><b>Tools</b> — registered here via {@link ToolCallbackProvider}. Spring
 * AI introspects all {@code @Tool}-annotated methods, generates JSON Schema
 * from parameter types and {@code @ToolParam} descriptions, and serves them via
 * {@code tools/list}.</li>
 *
 * <li><b>Resources</b> — registered in
 * {@link com.sample.app.resources.EmployeeResourceRegistrar} as a
 * {@code List<McpStatelessServerFeatures.SyncResourceSpecification>} bean. The
 * autoconfigure flat-maps all such beans via {@code ObjectProvider}.</li>
 *
 * <li><b>Prompts</b> — registered in
 * {@link com.sample.app.prompts.EmployeePromptRegistrar} as a
 * {@code List<McpStatelessServerFeatures.SyncPromptSpecification>} bean. Same
 * flat-map mechanism.</li>
 * </ol>
 *
 * <p>
 * <b>STATELESS transport note:</b> Because
 * {@code spring.ai.mcp.server.protocol=STATELESS} is set in
 * {@code application.yml}, the server uses {@code McpStatelessSyncServer} (not
 * {@code McpSyncServer}). This requires the stateless variants of the
 * specification types:
 * {@code McpStatelessServerFeatures.SyncResourceSpecification} and
 * {@code McpStatelessServerFeatures.SyncPromptSpecification}.
 */
@Configuration
public class McpConfig {

	/**
	 * Registers all {@link EmployeeTools} {@code @Tool}-annotated methods as MCP
	 * tool callbacks.
	 *
	 * <p>
	 * {@link MethodToolCallbackProvider} uses reflection to scan the bean for
	 * {@code @Tool} methods and generates the JSON Schema descriptors that AI
	 * clients use to understand how to call each tool.
	 *
	 * @param employeeTools the bean containing all 15 tool methods
	 * @return the provider that the MCP auto-configuration consumes
	 */
	@Bean
	public ToolCallbackProvider employeeToolCallbackProvider(EmployeeTools employeeTools) {
		return MethodToolCallbackProvider.builder().toolObjects(employeeTools).build();
	}
}
