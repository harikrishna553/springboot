package com.sample.app.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chat")
@Tag(name = "Chat API", description = "AI Chat endpoints using Llama 3.2")
public class ChatController {

  @Autowired private ChatClient summaryChatClient;

  @Autowired private ChatClient storytellingChatClient;

  @PostMapping
  @Operation(
      summary = "Send a chat message via POST",
      description = "Send a message to the AI via POST request")
  public ResponseEntity<ChatResponse> chatPost(@RequestBody ChatRequest request) {
    try {
      String response = null;

      if (request.client == Client.SUMMARY_CLIENT) {
        response = summaryChatClient.prompt(request.message()).call().content();
      } else if (request.client == Client.STORY_TELLER) {
        response = storytellingChatClient.prompt(request.message()).call().content();
      } else {
        throw new IllegalArgumentException("Invalid Client");
      }

      return ResponseEntity.ok(new ChatResponse(request.message, response));
    } catch (Exception e) {
      return ResponseEntity.internalServerError()
          .body(new ChatResponse(request.message(), "Error: " + e.getMessage()));
    }
  }

  public static enum Client {
    SUMMARY_CLIENT,
    STORY_TELLER
  }

  // DTO classes
  public static record ChatRequest(String message, Client client) {}

  public record ChatResponse(String userMessage, String aiResponse) {}
}
