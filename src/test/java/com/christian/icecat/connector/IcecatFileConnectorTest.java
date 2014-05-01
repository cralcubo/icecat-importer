package com.christian.icecat.connector;

import static org.junit.Assert.assertNotNull;

import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;

public class IcecatFileConnectorTest {
	
	private static final String TEST_PATH = "./src/test/resources/TestIcecatIndexFile.xml";
	private IcecatFileConnector connector;
	
	@Before
	public void setUp(){
		connector = new IcecatFileConnector(Paths.get(TEST_PATH));
	}
	
	@Test
	public void testOpenConnection() throws Exception{
		connector.open();
		assertNotNull(connector.getConnectionStream());
		connector.close();
	}

}
