package com.christian.icecat.parser;

import static org.junit.Assert.assertNotNull;

import java.nio.file.Paths;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import com.christian.icecat.connector.IcecatFileConnector;
import com.christian.icecat.exceptions.IcecatConnectionException;
import com.christian.icecat.exceptions.IcecatParsingException;
import com.christian.icecat.model.index.IcecatFile;
import com.christian.icecat.model.index.IcecatFileWrapper;
import com.christian.icecat.utility.JavaVersionChecker;

public class IcecatIndexParserTest {
	private static final String FILE_PATH = "./src/test/resources/TestIcecatIndexFile.xml";
	private IcecatIndexParser parser;
	
	@Before
	public void setUp(){
		if(JavaVersionChecker.isJava8OrGreater())
		{
			System.setProperty("javax.xml.accessExternalDTD", "all");
		}
		IcecatFileConnector connector = new IcecatFileConnector(Paths.get(FILE_PATH)); 
		parser = new IcecatIndexParser(connector);
	}
	
	@Test
	public void testParseStream() throws IcecatConnectionException, IcecatParsingException {
		IcecatFileWrapper icecatObject = parser.parseStream(IcecatFileWrapper.class);
		assertNotNull(icecatObject);
		List<IcecatFile> files = icecatObject.getFiles();
		assertTrue("Number of files expected is 2", files.size() == 2);
		System.out.println(files.get(0).toString());
	}
	
	

}
