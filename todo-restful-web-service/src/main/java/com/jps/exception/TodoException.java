package com.jps.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="TODO ID Not Found")
public class TodoException extends RuntimeException {
	
	private static final long serialVersionUID = -8200569022290568747L;

	public TodoException(String message) {
		super(message);
	}

}
