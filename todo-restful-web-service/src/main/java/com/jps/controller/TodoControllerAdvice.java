package com.jps.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.jps.exception.TodoException;


@ControllerAdvice
public class TodoControllerAdvice extends ResponseEntityExceptionHandler {
	
	Logger logger = LoggerFactory.getLogger(TodoControllerAdvice.class);

	@ExceptionHandler(TodoException.class)
	public ResponseEntity<Object> handleBadRequestException(TodoException exception) {
		
		logger.error("Exception thrown due to bad request {}" , exception.getMessage());
		
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}
	

}
