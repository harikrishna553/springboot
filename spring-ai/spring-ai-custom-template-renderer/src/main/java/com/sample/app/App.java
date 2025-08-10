package com.sample.app;

import com.sample.app.util.AngleBracketTemplateRenderer;
import jakarta.annotation.PostConstruct;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {

  @Autowired private ChatClient chatClient;

  @PostConstruct
  public void demo() {

    String template =
        """
				Summarize this profile in single line:
				{
				  "name": "<name>",
				  "age": "<age>",
				  "country" : "<country>"
				}
				""";

    String response =
        chatClient
            .prompt()
            .user(
                u ->
                    u.text(template)
                        .param("name", "Ramakrishna")
                        .param("age", 37)
                        .param("country", "India"))
            .templateRenderer(new AngleBracketTemplateRenderer())
            .call()
            .content();

    System.out.println(response);
  }

  public static void main(String[] args) {
    SpringApplication.run(App.class, args);
  }
}
