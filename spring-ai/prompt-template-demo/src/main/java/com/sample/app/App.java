package com.sample.app;

import jakarta.annotation.PostConstruct;
import java.util.Map;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;

@SpringBootApplication
public class App {

  @Autowired private ChatClient chatClient;

  public static void main(String[] args) {
    SpringApplication.run(App.class, args);
  }

  @PostConstruct
  public void demo() {

    // Simple template without variables
    System.out.println("\n********** Example 1: Simple template without variables **********");
    PromptTemplate t1 = PromptTemplate.builder().template("Hello AI!").build();
    System.out.println(chatClient.prompt(t1.create()).call().content());

    // Template with variables
    System.out.println("\n********** Example 2: Template with variables **********");
    PromptTemplate t2 =
        PromptTemplate.builder()
            .template("Hello, {name}! Tell me something about {city}?")
            .variables(Map.of("name", "Hari", "city", "Hyderabad"))
            .build();
    System.out.println(chatClient.prompt(t2.create()).call().content());

    // Template with variable injected at runtime
    System.out.println(
        "\n********** Example 3: Template with variable injected at runtime **********");
    PromptTemplate t3 = PromptTemplate.builder().template("Translate '{text}' to French.").build();
    System.out.println(
        chatClient.prompt(t3.create(Map.of("text", "Good morning"))).call().content());

    // Template from resource file (classpath)
    System.out.println(
        "\n********** Example 4: Template from resource file (classpath) **********");
    PromptTemplate t4 =
        PromptTemplate.builder()
            .resource(new ClassPathResource("templates/greeting.txt"))
            .variables(Map.of("name", "Krishna"))
            .build();
    System.out.println(chatClient.prompt(t4.create()).call().content());

    // Using mutate() to change an existing template
    System.out.println(
        "\n********** Example 5: Using mutate() to change an existing template **********");
    PromptTemplate t5 = t2.mutate().variables(Map.of("name", "Krishna", "city", "Delhi")).build();
    System.out.println(chatClient.prompt(t5.create()).call().content());
  }
}
