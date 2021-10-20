package com.zkteco.book.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	// handling specific exception
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorDetails> resourceNotFoundHandling(ResourceNotFoundException exception, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), HttpStatus.NOT_FOUND, exception.getMessage(), request.getDescription(false));

		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);

	}

	// handling global exception
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> globalExceptionHandling(Exception exception, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), HttpStatus.NOT_FOUND, exception.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// handling global exception
	public ResponseEntity<ErrorDetails> handleAPIException(APIException apiException, WebRequest request) {

		ErrorDetails errorDetails = new ErrorDetails(new Date(), HttpStatus.NOT_FOUND, apiException.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);

	}

}
