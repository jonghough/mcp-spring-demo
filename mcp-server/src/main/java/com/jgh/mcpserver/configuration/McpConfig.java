package com.jgh.mcpserver.configuration;

import com.jgh.mcpserver.service.AppointmentService;
import com.jgh.mcpserver.service.DoctorService;
import com.jgh.mcpserver.service.PatientService;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class McpConfig {
  @Bean
  public ToolCallbackProvider createToolCallbackProvider(
      PatientService patientService,
      DoctorService doctorService,
      AppointmentService appointmentService) {
    return MethodToolCallbackProvider.builder()
        .toolObjects(patientService, doctorService, appointmentService)
        .build();
  }
}
