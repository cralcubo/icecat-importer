package com.christian.icecat.connectable;


/**
 * Interface that sets the signature of
 * all the classes that need to connect
 * to a source or destiny of information.
 * 
 * @author christian
 *
 */
public interface Connectable extends AutoCloseable {
	
	void open() throws Exception;
	
	void connect() throws Exception;

}
