package com.jgh.mcpclient.configuration;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.ai.ollama.api.OllamaModel;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class McpClientConfig {

  public static final OllamaModel OLLAMA_MODEL = OllamaModel.LLAMA3_1;

  @Bean
  public ChatClient createChatClient(ToolCallbackProvider tools) {

    var ollamaApi = new OllamaApi();
    var chatModel =
        OllamaChatModel.builder()
            .ollamaApi(ollamaApi)
            .defaultOptions(
                OllamaOptions.builder()
                    .model(OLLAMA_MODEL)
                    .temperature(0.4) // TODO not hardcode
                    .build())
            .build();

    var chatClientBuilder = ChatClient.builder(chatModel);

    return chatClientBuilder.defaultTools(tools).build();
  }
}
