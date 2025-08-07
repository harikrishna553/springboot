package com.sample.app;

import jakarta.annotation.PostConstruct;
import java.util.List;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {

  @Autowired private ChatClient chatClient;

  public static void main(String[] args) {
    SpringApplication.run(App.class, args);
  }

  // Record to hold extracted structured data
  static record PersonHobbies(String person, List<String> hobbies) {}

  @PostConstruct
  public void demo() {
    String inputText =
        """
				Krishna is a software engineer who enjoys painting, cycling on weekends, and collecting vintage stamps.
				In his free time, he also likes experimenting with new cooking recipes.
				""";

    PersonHobbies extracted =
        chatClient
            .prompt()
            .system(
                "You extract structured data. Given a paragraph, return only the JSON with person name and hobbies.")
            .user(
                """
						Text: %s

						Extract the person's full name and a list of hobbies from above content.
						"""
                    .formatted(inputText))
            .call()
            .entity(PersonHobbies.class);

    System.out.println("Extracted:\n" + extracted);
  }
}

