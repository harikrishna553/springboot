package com.sample.app;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.annotation.PostConstruct;
import reactor.core.publisher.Flux;

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
    BeanOutputConverter<PersonHobbies> converter = new BeanOutputConverter<>(PersonHobbies.class);

    String userInput =
        """
				Krishna is a software engineer who enjoys painting, cycling on weekends, and collecting vintage stamps.
				In his free time, he also likes experimenting with new cooking recipes.
				""";

    String prompt =
        """
				Text: {userInput}
				{format}
				Extract the person's full name and a list of hobbies from above content.
				""";

    Flux<String> flux =
        chatClient
            .prompt()
            .system(
                "You extract structured data. Given a paragraph, return only the JSON with person name and hobbies.")
            .user(
                u ->
                    u.text(prompt)
                        .param("format", converter.getFormat())
                        .param("userInput", userInput))
            .stream()
            .content();

    String content = flux.collectList().block().stream().collect(Collectors.joining());
    PersonHobbies personHobbies = converter.convert(content);

    System.out.println("***********************************");
    System.out.println(personHobbies);
    System.out.println("***********************************");
  }
}
