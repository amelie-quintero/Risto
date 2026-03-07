package com.example.Risto.audit;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;

public class TestAuditorAware implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {
		return Optional.of("test-user");
	}

}
