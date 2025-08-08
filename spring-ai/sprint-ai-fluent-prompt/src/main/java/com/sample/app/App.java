package com.sample.app;

import jakarta.annotation.PostConstruct;
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

  @PostConstruct
  public void demo() {

    String answer =
        chatClient
            .prompt()
            .user(
                u ->
                    u.text("Tell me the top must {n} places to visit in the country {country}")
                        .param("n", 5)
                        .param("country", "India"))
            .call()
            .content();

    System.out.println(answer);
  }
}
