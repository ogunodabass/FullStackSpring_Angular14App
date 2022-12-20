package com.example.demo.util.functions;

import java.util.Set;

import com.example.demo.config.CollectionConfig;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Functions {

	private static void exceptionally(Throwable var1) {
		log.error("exceptionally: ", var1);
	}

	@SuppressWarnings("rawtypes")
	public static Set exceptionallySet(Throwable var1) {
		exceptionally(var1);
		return  CollectionConfig.unmodifiableSet();

	}

}
