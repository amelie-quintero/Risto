package com.example.Risto.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration  
@EnableJpaAuditing(auditorAwareRef = "mainAuditorAware")
public class AuditorConfig {  
}  