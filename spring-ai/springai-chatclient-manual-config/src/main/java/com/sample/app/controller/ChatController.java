package com.sample.app.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chat")
@Tag(name = "Chat API", description = "AI Chat endpoints using Llama 3.2")
public class ChatController {

  @Autowired private ChatClient chatClient;

  @GetMapping
  @Operation(
      summary = "Send a chat message",
      description = "Send a message to the AI and get a response")
  public ResponseEntity<ChatResponse> chat(
      @Parameter(description = "The message to send to the AI", example = "Hello, how are you?")
          @RequestParam(defaultValue = "Hello")
          String message) {
    try {
      String response = chatClient.prompt(message).call().content();
      return ResponseEntity.ok(new ChatResponse(message, response));
    } catch (Exception e) {
      return ResponseEntity.internalServerError()
          .body(new ChatResponse(message, "Error: " + e.getMessage()));
    }
  }

  @PostMapping
  @Operation(
      summary = "Send a chat message via POST",
      description = "Send a message to the AI via POST request")
  public ResponseEntity<ChatResponse> chatPost(@RequestBody ChatRequest request) {
    try {
      String response = chatClient.prompt(request.getMessage()).call().content();
      return ResponseEntity.ok(new ChatResponse(request.getMessage(), response));
    } catch (Exception e) {
      return ResponseEntity.internalServerError()
          .body(new ChatResponse(request.getMessage(), "Error: " + e.getMessage()));
    }
  }

  // DTO classes
  public static class ChatRequest {
    private String message;

    public ChatRequest() {}

    public ChatRequest(String message) {
      this.message = message;
    }

    public String getMessage() {
      return message;
    }

    public void setMessage(String message) {
      this.message = message;
    }
  }

  public static class ChatResponse {
    private String userMessage;
    private String aiResponse;

    public ChatResponse() {}

    public ChatResponse(String userMessage, String aiResponse) {
      this.userMessage = userMessage;
      this.aiResponse = aiResponse;
    }

    public String getUserMessage() {
      return userMessage;
    }

    public void setUserMessage(String userMessage) {
      this.userMessage = userMessage;
    }

    public String getAiResponse() {
      return aiResponse;
    }

    public void setAiResponse(String aiResponse) {
      this.aiResponse = aiResponse;
    }
  }
}
