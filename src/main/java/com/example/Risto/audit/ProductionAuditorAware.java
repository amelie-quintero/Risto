package com.example.Risto.audit;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component("mainAuditorAware")
public class ProductionAuditorAware implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();  
        if (authentication == null || !authentication.isAuthenticated()) {  
            return Optional.of("system");
        }  
        return Optional.of(authentication.getName());
	}
}
