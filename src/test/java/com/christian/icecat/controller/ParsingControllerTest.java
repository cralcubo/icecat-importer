package com.christian.icecat.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import com.christian.icecat.connector.AbstractIcecatConnector;
import com.christian.icecat.exceptions.IcecatConnectionException;
import com.christian.icecat.exceptions.IcecatParsingException;
import com.christian.icecat.model.index.IcecatFile;
import com.christian.icecat.model.index.IcecatFileWrapper;
import com.christian.icecat.model.product.ICECATInterface;
import com.christian.icecat.model.product.Product;
import com.christian.icecat.parser.IcecatIndexParser;
import com.christian.icecat.parser.IcecatProductParser;

public class ParsingControllerTest {
	
	private ParsingController controller;
	
	@Before
	public void setUp(){
		AbstractIcecatConnector connector = EasyMock.createMock(AbstractIcecatConnector.class);
		controller = new ParsingController(connector);
	}
	
	@Test
	public void testGetAllProductsId() throws IcecatConnectionException, IcecatParsingException, IOException{
		IcecatIndexParser indexParser = EasyMock.createMock(IcecatIndexParser.class);
		
		IcecatFileWrapper fileWrapper = new IcecatFileWrapper();
		
		List<IcecatFile> files = new ArrayList<IcecatFile>();
		
		IcecatFile file = new IcecatFile();
		
		long anId = 1L;
		String aDateStr = "a Date";
		file.setProductId(anId);
		file.setUpdate(aDateStr);
		
		files.add(file);
		
		fileWrapper.setFiles(files);
		
		EasyMock.expect(indexParser.parseStream(IcecatFileWrapper.class)).andReturn(fileWrapper );
		EasyMock.replay(indexParser);
		
		controller.setIndexParser(indexParser );
		
		Map<Long, String> idMap = controller.getAllProductsId();
		
		assertTrue("Only 1 map element was expected", idMap.size() == 1);
		assertEquals(String.format("The expected value for id %d was %s", anId, aDateStr), idMap.get(anId), aDateStr);
		
		EasyMock.verify(indexParser);
	}
	
	@Test
	public void testGetIcecatProduct() throws IcecatConnectionException, IcecatParsingException{
		long id = 1L;
		
		IcecatProductParser productParser = EasyMock.createMock(IcecatProductParser.class);
		IcecatProductParser newParser = EasyMock.createMock(IcecatProductParser.class);
		EasyMock.expect(productParser.cloneProductParser()).andReturn(newParser );
		
		newParser.setProductId(id);
		ICECATInterface icecatInterface = new ICECATInterface();
		Product aProduct = new Product();
		icecatInterface.setProduct(aProduct );
		EasyMock.expect(newParser.parseStream(ICECATInterface.class)).andReturn(icecatInterface );
		
		EasyMock.replay(productParser, newParser);
		
		controller.setProductParser(productParser );
		Product product = controller.getIcecatProduct(id);
		
		assertEquals("A different product was returned", aProduct, product);
	}

}
