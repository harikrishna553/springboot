package com.sample.app;

import jakarta.annotation.PostConstruct;
import java.util.Arrays;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
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
    // Example 1: Simple system + user message via fluent API
    String response =
        chatClient
            .prompt()
            .system("You are a helpful assistant.")
            .user("What is the capital of France?")
            .call()
            .content();
    System.out.println("Q: What is the capital of France?\nA: " + response);
    System.out.println("------------------------------------------------");

    // Example 2: Quick one-liner prompt
    response = chatClient.prompt("Tell me a joke.").call().content();
    System.out.println("Q: Tell me a joke.\nA: " + response);
    System.out.println("------------------------------------------------");

    // Example 3: Prompt built using Prompt.builder() with a single user message
    Prompt prompt =
        Prompt.builder()
            .messages(
                Arrays.asList(
                    UserMessage.builder().text("Summarize the Spring framework.").build()))
            .build();

    response = chatClient.prompt(prompt).call().content();
    System.out.println("Q: Summarize the Spring framework.\nA: " + response);
    System.out.println("------------------------------------------------");

    // Example 4: Multi-turn conversation with system, user, and assistant messages
    prompt =
        Prompt.builder()
            .messages(
                Arrays.asList(
                    SystemMessage.builder()
                        .text("You are a knowledgeable and friendly assistant.")
                        .build(),
                    UserMessage.builder().text("Hi, my name is Harikrishna.").build(),
                    new AssistantMessage("Hello Harikrishna! How can I help you today?"),
                    UserMessage.builder().text("What is my name?").build()))
            .build();

    response = chatClient.prompt(prompt).call().content();
    System.out.println("Q: What is my name?\nA: " + response);
    System.out.println("------------------------------------------------");
  }
}
