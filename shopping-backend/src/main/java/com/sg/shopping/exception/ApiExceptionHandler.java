package com.sg.shopping.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.Data;

/**
 * Handle global exception
 * 
 * @author Samuel
 *
 */
@RestControllerAdvice
public class ApiExceptionHandler {

	@ExceptionHandler(ProcessException.class)
	@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
	public ResponseEntity<ErrorResponse> handle(ProcessException e) {
		ErrorResponse error = new ErrorResponse();
		error.setCode(HttpStatus.NOT_ACCEPTABLE.value());
		error.setMessage(e.getMessage());
		return new ResponseEntity<>(error, HttpStatus.NOT_ACCEPTABLE);
	}

	/**
	 * Error Model
	 * 
	 * @author Samuel
	 *
	 */
	@Data
	class ErrorResponse {
		private int code;

		private String message;

	}

}