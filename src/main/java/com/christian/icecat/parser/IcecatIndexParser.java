package com.christian.icecat.parser;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import com.christian.icecat.connector.AbstractIcecatConnector;
import com.christian.icecat.model.index.IcecatFileWrapper;

/**
 * This is the class that will parse the 
 * basic information of all the products that
 * are available in ICECAT.
 * 
 * The object @IcecatFileWrapper is the one that
 * contains all the basic information of all the
 * products available in ICECAT. 
 * 
 * @author christian
 *
 */
public class IcecatIndexParser extends AbstractIcecatParser<IcecatFileWrapper>{

	public IcecatIndexParser(AbstractIcecatConnector connector) {
		super(connector);
	}

	@Override
	protected JAXBContext createJAXBContext() throws JAXBException {
		return JAXBContext.newInstance(IcecatFileWrapper.class);
	}

}
