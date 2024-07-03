package com.employee.exception;

import java.time.LocalDate;

public class BussinessException extends RuntimeException {

	private static final long serialVersionUID = 6051893548191385029L;
	
	private String message;
	private String details;
	private LocalDate date;

	public BussinessException() {
		super();
	}

	public BussinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public BussinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public BussinessException(String message) {
		super(message);
	}

	public BussinessException(Throwable cause) {
		super(cause);
	}

	@Override
	public synchronized Throwable fillInStackTrace() {
		return super.fillInStackTrace();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public BussinessException(String message, String details, LocalDate date) {
		super();
		this.message = message;
		this.details = details;
		this.date = date;
	}
	


}
