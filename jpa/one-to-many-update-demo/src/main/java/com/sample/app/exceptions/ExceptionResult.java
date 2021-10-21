package com.sample.app.exceptions;

public class ExceptionResult {
	private String message;

	public ExceptionResult(AppException ex) {
		this.message = ex.getMessage();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
