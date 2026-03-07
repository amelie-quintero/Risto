package com.example.Risto.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.example.Risto.audit.TestAuditorAware;

@Configuration  
@EnableJpaAuditing(auditorAwareRef = "testAuditorAware")
public class TestAuditorConfig {  
 
    @Bean("testAuditorAware")  
    @Primary
    public AuditorAware<String> testAuditorAware() {  
        return new TestAuditorAware();  
    }  
}  