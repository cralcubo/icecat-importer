package com.christian.icecat.exceptions;

public class IcecatNullIdException extends Exception {
	private static final long serialVersionUID = 1L;

	private static final String DEFAULT_MESSAGE = "An Icecat object without ID was found";
	
	public IcecatNullIdException() {
		super(DEFAULT_MESSAGE);
	}
	
	public IcecatNullIdException(String message) {
		super(message);
	}

}
