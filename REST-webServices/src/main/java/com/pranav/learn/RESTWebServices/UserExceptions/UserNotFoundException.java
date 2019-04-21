package com.pranav.learn.RESTWebServices.UserExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {


	private static final long serialVersionUID = -7161259637168496831L;

	public UserNotFoundException(String message) {
		super(message);
	}
	
	
	
}
