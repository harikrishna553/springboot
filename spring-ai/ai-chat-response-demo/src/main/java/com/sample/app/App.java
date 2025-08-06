package com.sample.app;

import jakarta.annotation.PostConstruct;
import java.util.List;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.metadata.ChatResponseMetadata;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.model.Generation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {

  @Autowired private ChatClient chatClient;

  public static void main(String[] args) {
    SpringApplication.run(App.class, args);
  }

  @PostConstruct
  public void demo() {
    ChatResponse chatResponse = chatClient.prompt().user("Tell me a short story").call().chatResponse();

    // Print each generation (AI's output)
    List<Generation> generations = chatResponse.getResults();
    System.out.println("=== AI Generated Responses ===");
    for (int i = 0; i < generations.size(); i++) {
      Generation generation = generations.get(i);
      System.out.println("Response #" + (i + 1) + ": " + generation.getOutput().getText());
    }

    // Print metadata (e.g., token usage)
    ChatResponseMetadata metadata = chatResponse.getMetadata();
    System.out.println("=== Metadata ===");
    System.out.println("Usage: " + metadata.getUsage());
    System.out.println("Model: " + metadata.getModel());
  }
}
