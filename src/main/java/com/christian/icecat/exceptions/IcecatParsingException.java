package com.christian.icecat.exceptions;

public class IcecatParsingException extends Exception{
	
	private static final long serialVersionUID = -8165322291706351950L;
	private static final String DEFAULT_MESSAGE = "There was an error parsing the XML file";

	public IcecatParsingException() {
		super(DEFAULT_MESSAGE);
	}
	
	public IcecatParsingException(String message){
		super(message);
	}

}
