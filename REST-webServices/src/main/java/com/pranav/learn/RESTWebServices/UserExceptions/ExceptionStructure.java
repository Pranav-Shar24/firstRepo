package com.pranav.learn.RESTWebServices.UserExceptions;

import java.util.Date;

public class ExceptionStructure {
	private Date timestamp;
	private String details;
	private String message;

	public ExceptionStructure(Date timestamp, String details, String message) {
		super();
		this.timestamp = timestamp;
		this.details = details;
		this.message = message;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public String getDetails() {
		return details;
	}

	public String getMessage() {
		return message;
	}

}
