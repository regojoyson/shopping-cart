package com.sg.shopping.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Runtime Exception Implementation for Generic Processing Exception
 * 
 * @author Samuel
 *
 */
@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
public class ProcessException extends RuntimeException {

	/**
	 * Default Serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Exception Message
	 * 
	 * @param msg Exception Message
	 */
	public ProcessException(String msg) {
		super(msg);
	}

	/**
	 * Bad Request Exception with a Valid Message and Exception
	 * 
	 * @param msg       Exception
	 * @param throwable Exception that has been thrown
	 */
	public ProcessException(String msg, Throwable throwable) {
		super(msg, throwable);
	}
}
