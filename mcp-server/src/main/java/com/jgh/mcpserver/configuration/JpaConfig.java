package com.jgh.mcpserver.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.jgh.mcpserver.repository")
public class JpaConfig {}
