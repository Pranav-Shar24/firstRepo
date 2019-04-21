package com.pranav.learn.RESTWebServices.UserExceptions;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityException extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException(Exception exception, WebRequest request) {

		ExceptionStructure exceptionStructure = new ExceptionStructure(new Date(), exception.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<>(exceptionStructure, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {

		ExceptionStructure exceptionStructure = new ExceptionStructure(new Date(), ex.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<>(exceptionStructure, HttpStatus.NOT_FOUND);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExceptionStructure exceptionStructure = new ExceptionStructure(new Date(), "Validation Failed!!",
				ex.getBindingResult().toString());

		return new ResponseEntity<>(exceptionStructure, HttpStatus.BAD_REQUEST);
	}
}
