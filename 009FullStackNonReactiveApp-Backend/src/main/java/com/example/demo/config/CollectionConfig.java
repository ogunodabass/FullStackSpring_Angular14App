package com.example.demo.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CollectionConfig {
	
	@SuppressWarnings("rawtypes")
	@Bean
	public static Map unmodifiableMap() {
		return Map.of();
	}

	@SuppressWarnings("rawtypes")
	@Bean
	public static List unmodifiableCollection() {
		return Collections.unmodifiableList(new ArrayList<>(0));
	}

	@SuppressWarnings("rawtypes")
	@Bean
	public static Set unmodifiableSet() {
		return Collections.unmodifiableSet(new HashSet<>(0));
	}

}
