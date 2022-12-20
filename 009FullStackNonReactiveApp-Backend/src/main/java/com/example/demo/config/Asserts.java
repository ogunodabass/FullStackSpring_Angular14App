package com.example.demo.config;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import lombok.RequiredArgsConstructor;

@Order(Integer.MIN_VALUE)
@Component
@RequiredArgsConstructor
public class Asserts {
	
	private final ConfigurableApplicationContext context;
	
	@PostConstruct
	public void init() {
		
		Assert.isTrue(TimeZone.getTimeZone("Europe/Istanbul")!=TimeZone.getDefault(), "Timezone not Europe/Istanbul");
		Assert.isTrue(!context.getEnvironment().getProperty("spring.jpa.hibernate.ddl-auto").equalsIgnoreCase("none"),"spring.jpa.hibernate.ddl-auto==none");

	}
	
}
