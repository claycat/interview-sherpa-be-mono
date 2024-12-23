package com.sherpa.interviewsherpa.configuration;

import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class FlywayConfig {

	@Bean
	public FlywayMigrationStrategy cleanMigrateStrategy() {
		return flyway -> {
			flyway.clean();
			flyway.migrate();
		};
	}
}
