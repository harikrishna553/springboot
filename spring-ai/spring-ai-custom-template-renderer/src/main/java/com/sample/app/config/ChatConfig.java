package com.sample.app.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatConfig {

  @Bean
  ChatClient chatClient() {
    // 1. Create the Ollama API client
    OllamaApi ollamaApi = OllamaApi.builder().baseUrl("http://localhost:11434").build();

    OllamaOptions defaultOptions =
        OllamaOptions.builder().model("llama3.2").temperature(0.7).build();

    // 2. Define the chat options
    OllamaChatModel ollamaChatModel =
        OllamaChatModel.builder().ollamaApi(ollamaApi).defaultOptions(defaultOptions).build();

    ChatClient chatClient = ChatClient.builder(ollamaChatModel).build();

    return chatClient;
  }
}
