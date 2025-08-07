package com.sample.app;

import jakarta.annotation.PostConstruct;
import java.util.List;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.*;

@SpringBootApplication
public class App {

  @Autowired private ChatClient chatClient;

  public static void main(String[] args) {
    SpringApplication.run(App.class, args);
  }

  // Record to hold extracted structured data
  static record Book(String title, List<String> authors) {}

  @PostConstruct
  public void demo() {
    String inputText =
        """
				I’ve recently read several interesting books.
				“Atomic Habits” by James Clear was incredibly insightful for building better habits.
				Another great one was “Thinking, Fast and Slow” by Daniel Kahneman, which dives deep into how our brains make decisions.
				I also enjoyed “The Pragmatic Programmer” by Andrew Hunt and David Thomas — it’s a must-read for software engineers.
				""";

    List<Book> books =
        chatClient
            .prompt()
            .user(
                """
				Text: %s
				"""
                    .formatted(inputText))
            .call()
            .entity(new ParameterizedTypeReference<List<Book>>() {});

    System.out.println("Extracted:\n" + books);
  }
}