package com.jgh.mcpclient;

import org.springframework.ai.autoconfigure.mcp.client.SseHttpClientTransportAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@ImportAutoConfiguration(exclude = {SseHttpClientTransportAutoConfiguration.class})
public class McpClientApplication {

  public static void main(String[] args) {
    SpringApplication.run(McpClientApplication.class, args);
  }
}
