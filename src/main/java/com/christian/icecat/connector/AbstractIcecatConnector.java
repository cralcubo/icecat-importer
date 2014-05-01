package com.christian.icecat.connector;

import java.io.IOException;
import java.io.InputStream;

import com.christian.icecat.connectable.Connectable;
import com.christian.icecat.exceptions.IcecatConnectionException;


public abstract class AbstractIcecatConnector implements Connectable {
	protected InputStream connectionStream;
	
	public abstract void open() throws IOException, IcecatConnectionException;
	
	public void connect() throws IOException{
		connectionStream.available();
	}
	
	public void close() throws Exception {
		if(connectionStream != null){
			try {
				connectionStream.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		
	}
	
	public InputStream getConnectionStream(){
		return this.connectionStream;
	}
}
