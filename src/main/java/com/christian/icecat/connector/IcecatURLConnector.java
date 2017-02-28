package com.christian.icecat.connector;

import java.io.IOException;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.christian.icecat.exceptions.IcecatConnectionException;

/**
 * Class that can connect to the Icecat server open a connection, 
 * get the stream and close the connection.
 * 
 * @author christian
 * 
 */
public class IcecatURLConnector extends AbstractIcecatConnector{
	private final Logger LOG = LoggerFactory.getLogger(IcecatURLConnector.class);
	
	private final String username;
	private final String password;
	private String url;

	public IcecatURLConnector(String username, String password, String url) {
		this.username = username;
		this.password = password;
		this.setUrl(url);
	}

	public IcecatURLConnector(IcecatURLConnector connector) {
		this(connector.username, connector.password, connector.url);
	}

	public void open() throws IcecatConnectionException {
		
		try {
			//Connections to Icecat needs an authentication
			LOG.debug("Connector: {} Connecting to ICECAT with URL: {}", this, getUrl());
			Authenticator.setDefault(new IcecatAuthenticator(username, password));
			connectionStream = new URL(getUrl()).openStream();
			LOG.debug("Successfully connected to ICECAT");
		} catch (IOException e) {
			LOG.error("Error connecting to ICECAT", e);
			throw new IcecatConnectionException("Error connecting to ICECAT");
		}
	}
	
	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * Icecat needs an authentication to 
	 * retrieve its information.
	 * 
	 * @author christian
	 *
	 */
	private class IcecatAuthenticator extends Authenticator {
		private final String username;
		private final String password;
		
		IcecatAuthenticator(String username, String password){
			this.username = username;
			this.password = password;
		}
		@Override
		protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(username, password.toCharArray());
		}
	}

}
