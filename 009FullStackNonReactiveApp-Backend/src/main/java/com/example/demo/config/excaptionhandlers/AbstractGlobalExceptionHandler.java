package com.example.demo.config.excaptionhandlers;

import java.sql.SQLException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "AbstractGlobalExceptionHandler")
@RestControllerAdvice
public abstract class AbstractGlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Validation error.");
		for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
			errorResponse.addValidationError(fieldError.getField(), fieldError.getDefaultMessage());
		}
		return ResponseEntity.unprocessableEntity().body(errorResponse);
	}

	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	protected ResponseEntity<Object> handleNoSuchElementFoundException(RuntimeException exception, WebRequest request) {
		log.error(exception.getMessage(), exception);
		return buildErrorResponse(exception, HttpStatus.NOT_FOUND, request);
	}

	@ExceptionHandler(SQLException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	protected abstract ResponseEntity<Object> handleSQLException(SQLException exception, WebRequest request);

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	protected ResponseEntity<Object> handleAllUncaughtException(Exception exception, WebRequest request) {
		log.error("Unknown error occurred", exception);
		return buildErrorResponse(exception, "Unknown error occurred", HttpStatus.INTERNAL_SERVER_ERROR, request);
	}

	protected final ResponseEntity<Object> buildErrorResponse(Exception exception, HttpStatus httpStatus,
			WebRequest request) {
		return buildErrorResponse(exception, exception.getMessage(), httpStatus, request);
	}

	protected final ResponseEntity<Object> buildErrorResponse(Exception exception, String message,
			HttpStatus httpStatus, WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse(httpStatus.value(), message);
		StringBuilder builder = new StringBuilder(0);
		for (var o : exception.getStackTrace())
			builder.append(o).append(System.getProperty("line.separator"));
		errorResponse.setStackTrace(builder.toString());

		return ResponseEntity.status(httpStatus).body(errorResponse);
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		return buildErrorResponse(ex, status, request);
	}
}