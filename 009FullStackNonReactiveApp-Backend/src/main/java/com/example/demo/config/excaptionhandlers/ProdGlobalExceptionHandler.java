package com.example.demo.config.excaptionhandlers;


import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.example.demo.util.config.enums.AppProfiles;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "ProdGlobalExceptionHandler")
@Profile(AppProfiles.PROD)
@RestControllerAdvice
public class ProdGlobalExceptionHandler extends AbstractGlobalExceptionHandler{

	@Lazy
	@Autowired
	private ConfigurableApplicationContext context;
	
	@Override
	protected ResponseEntity<Object> handleSQLException(SQLException exception, WebRequest request) {
		log.error(exception.getMessage(), exception);
		return super.buildErrorResponse(context.getBean(SQLException.class), HttpStatus.NOT_FOUND, request);
	}

}
