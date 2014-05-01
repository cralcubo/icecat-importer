package com.christian.icecat.connector;

import org.junit.Before;
import org.junit.Test;

import com.christian.icecat.exceptions.IcecatConnectionException;

public class IcecatUrlConnectorTest {
	private static final String URL = "https://data.icecat.biz/export/freexml/NL/";
	private static final String PWD = "a_password";
	private static final String USER = "christian_roman" ;
	private IcecatURLConnector connector;
	
	@Before
	public void setUp() {
		connector = new IcecatURLConnector(USER, PWD, URL);
	}
	
	@Test(expected=IcecatConnectionException.class)
	public void testConnection() throws IcecatConnectionException {
		connector.open();
	}

}
