package com.artsiomtretinnikov.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = "com.artsiomtretinnikov.service")
@Import(PersistenceConfig.class)
public class ServiceConfiguration {
}
