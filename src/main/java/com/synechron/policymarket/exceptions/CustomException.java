package com.synechron.policymarket.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.synechron.policymarket.restutil.Response;

@ControllerAdvice
public class CustomException extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return getResponseEntity(ex.getBindingResult().getFieldError().getDefaultMessage());
	}

	private ResponseEntity<Object> getResponseEntity(String message) {
		return new ResponseEntity<Object>(new Response(message, null), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleException1(Exception ex, WebRequest request) {
		return getResponseEntity(ex.getMessage());
	}

	@ExceptionHandler(HealthPlanNotFoundException.class)
	public ResponseEntity<Object> handleHealthPlanNotFoundException(HealthPlanNotFoundException ex, WebRequest req) {
		return getResponseEntity(ex.getMessage());
	}

}
