package com.christian.icecat.parser;

import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.christian.icecat.connector.AbstractIcecatConnector;
import com.christian.icecat.exceptions.IcecatConnectionException;
import com.christian.icecat.exceptions.IcecatParsingException;

/**
 * Class that will use JAXB to unmarshall
 * the Icecat stream to an Icecat java object.
 * 
 * @author christian
 *
 * @param <T>
 */
public abstract class AbstractIcecatParser<T> {
	private final Logger LOG = LoggerFactory.getLogger(AbstractIcecatParser.class);
	private AbstractIcecatConnector connector;

	public AbstractIcecatParser(AbstractIcecatConnector connector) {
		this.setConnector(connector);
	}
	
	protected abstract JAXBContext createJAXBContext() throws JAXBException;
	
	public T parseStream(Class<T> clazz) throws IcecatConnectionException, IcecatParsingException {
		T icecatObject = null;
		JAXBContext context;

		if(getConnector() == null){
			throw new IcecatConnectionException("The stream conector (URL, FILE, etc...) cannot be null");
		}
		try {
			context = createJAXBContext();
			Unmarshaller um = context.createUnmarshaller();
			
			try{
				getConnector().open();
				icecatObject = clazz.cast(um.unmarshal(getConnector().getConnectionStream()));
			} catch (IOException e) {
				LOG.error("There was an error opening the icecat stream: {}",e.getMessage());
				throw new IcecatConnectionException();
			}
			
		} catch (JAXBException e) {
			LOG.error("There was an error unmarshalling the icecat xml stream: {}",e.getMessage());
			throw new IcecatParsingException();
		} 
		
		return icecatObject;
	}

	public AbstractIcecatConnector getConnector() {
		return connector;
	}

	public void setConnector(AbstractIcecatConnector connector) {
		this.connector = connector;
	}

}
