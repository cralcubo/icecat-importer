package com.christian.icecat.exceptions;

public class IcecatConnectionException extends Exception {

	private static final long serialVersionUID = 1123123L;
	private static final String DEFAULT_MESSAGE = "There was an error connecting to the icecat server";
	
	public IcecatConnectionException(String message) {
		super(message);
	}
	
	public IcecatConnectionException() {
		super(DEFAULT_MESSAGE);
	}

}
