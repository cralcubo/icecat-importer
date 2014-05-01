package com.christian.icecat.parser;

import static org.junit.Assert.*;

import java.math.BigInteger;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;

import com.christian.icecat.connector.AbstractIcecatConnector;
import com.christian.icecat.connector.IcecatFileConnector;
import com.christian.icecat.exceptions.IcecatConnectionException;
import com.christian.icecat.exceptions.IcecatParsingException;
import com.christian.icecat.model.product.ICECATInterface;
import com.christian.icecat.model.product.Product;

public class IcecatProductParserTest {
	private static final int PROD_ID = 8046;
	private static final String FILE_PATH = "./src/test/resources/product-%d.xml";
	private IcecatProductParser parser;
	
	@Before
	public void setUp() {
		
		AbstractIcecatConnector connector = new IcecatFileConnector(Paths.get(String.format(FILE_PATH, PROD_ID)));
		parser = new IcecatProductParser(connector);
	}
	
	@Test
	public void testParseStream() throws IcecatConnectionException, IcecatParsingException{
		ICECATInterface iceInterface = parser.parseStream(ICECATInterface.class);
		Product product = iceInterface.getProduct();
		assertEquals(String.format("The product Id expected is: %s", PROD_ID), product.getID(), BigInteger.valueOf(PROD_ID));
		assertFalse("Product description expected", product.getProductDescription().get(0).getLongDesc().isEmpty());
	}

}
