package com.example.demo.config.excaptionhandlers;

import java.sql.SQLException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;

import com.example.demo.util.config.enums.AppProfiles;

@Profile(AppProfiles.PROD)
@Configuration
public class ExceptionConfig {

	@Lazy
	@Bean
	private SQLException customSQLException() {
		return new SQLException("Sql Exception");
	}
}
