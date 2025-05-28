package com.jgh.mcpclient.controllers;

import com.jgh.mcpclient.dto.ToolRequest;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class McpClientController {

  @Autowired ChatClient chatClient;

  @PostMapping("/tool")
  public String tool(@RequestBody ToolRequest message) {
    System.out.println("Received message: " + message.message);

    PromptTemplate promptTemplate = new PromptTemplate(message.message);
    var response = chatClient.prompt(promptTemplate.create()).call().content();
    System.out.println("Response " + response);
    return response.toString();
  }
}
