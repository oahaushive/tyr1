package com.haushive.tyr1.exception;

public class WebApplicationException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public WebApplicationException() {
		
	}
	
	public WebApplicationException(String message) {
		super(message);
	}
	
	public WebApplicationException(Throwable cause) {
		super(cause);
	}
	
	public WebApplicationException(String message, Throwable cause) {
		super(message, cause);
	}

}
