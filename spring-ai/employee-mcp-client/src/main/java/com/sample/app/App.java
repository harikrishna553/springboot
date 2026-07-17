package com.sample.app;

import com.sample.app.agent.EmployeeAssistant;
import com.sample.app.console.ConsoleRenderer;
import dev.langchain4j.mcp.McpToolProvider;
import dev.langchain4j.mcp.client.DefaultMcpClient;
import dev.langchain4j.mcp.client.McpClient;
import dev.langchain4j.mcp.client.transport.McpTransport;
import dev.langchain4j.mcp.client.transport.http.StreamableHttpMcpTransport;
import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.ollama.OllamaChatModel;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.store.memory.chat.InMemoryChatMemoryStore;
import java.time.Duration;
import java.util.Scanner;
import java.util.UUID;

public class App {

  public static void main(String[] args) {

    try {

      // ----------------------------------------------------------
      // Generate Conversation Id
      // ----------------------------------------------------------

      String conversationId = UUID.randomUUID().toString();

      // ----------------------------------------------------------
      // Welcome Banner
      // ----------------------------------------------------------

      ConsoleRenderer.printBanner();
      ConsoleRenderer.info("Conversation : " + conversationId);
      ConsoleRenderer.info("Type 'exit' to quit.");
      System.out.println();

      // ----------------------------------------------------------
      // Configure Ollama
      // ----------------------------------------------------------

      ChatModel model =
          OllamaChatModel.builder()
              .baseUrl("http://localhost:11434")
              .modelName("qwen3.5")
              .timeout(Duration.ofMinutes(2))
              .build();

      // ----------------------------------------------------------
      // Shared Memory Store
      // ----------------------------------------------------------

      InMemoryChatMemoryStore memoryStore = new InMemoryChatMemoryStore();

      // ----------------------------------------------------------
      // Memory Provider
      // ----------------------------------------------------------

      ChatMemoryProvider memoryProvider =
          id ->
              MessageWindowChatMemory.builder()
                  .id(id)
                  .maxMessages(20)
                  .chatMemoryStore(memoryStore)
                  .build();

      // ----------------------------------------------------------
      // Build AI Agent
      // ----------------------------------------------------------

      McpTransport transport =
          StreamableHttpMcpTransport.builder()
              .url("http://localhost:8080/mcp")
              .logRequests(false)
              .logResponses(false)
              .build();

      McpClient client =
          DefaultMcpClient.builder().key("employee-client").transport(transport).build();
      McpToolProvider toolProvider = McpToolProvider.builder().mcpClients(client).build();

      EmployeeAssistant logicPuzzleSolver =
          AiServices.builder(EmployeeAssistant.class)
              .chatModel(model)
              .chatMemoryProvider(memoryProvider)
              .toolProvider(toolProvider)
              .build();

      // ----------------------------------------------------------
      // Conversation Loop
      // ----------------------------------------------------------

      Scanner scanner = new Scanner(System.in);

      while (true) {

        // Prompt
        ConsoleRenderer.printPrompt();

        String input = scanner.nextLine().trim();

        if (input.isBlank()) {
          continue;
        }

        if ("exit".equalsIgnoreCase(input) || "quit".equalsIgnoreCase(input)) {
          break;
        }

        ConsoleRenderer.thinking();

        String response = logicPuzzleSolver.chat(conversationId, input);

        ConsoleRenderer.agent(response);
      }

      System.out.println();
      ConsoleRenderer.info("Thanks for using Employee Agent 📚✨");

    } catch (Exception ex) {

      ConsoleRenderer.error("Unexpected error");
      ConsoleRenderer.error(ex.getMessage());
    }
  }
}
