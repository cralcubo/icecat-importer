package com.christian.icecat.controller;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import com.christian.icecat.exceptions.IcecatConnectionException;
import com.christian.icecat.exceptions.IcecatParsingException;
import com.christian.icecat.model.entities.SimpleProduct;
import com.christian.icecat.model.product.Product;

public class IcecatControllerTest {
	private IcecatController icecatController;
	private ParsingController parsingController;
	private DatabaseController databaseController;
	
	@Before
	public void setUp() {
		parsingController = EasyMock.createMock(ParsingController.class);
		databaseController = EasyMock.createMock(DatabaseController.class);
		icecatController = new IcecatController(parsingController, databaseController);
	}
	
	@Test
	public void testGetAllProductsId() throws IcecatConnectionException, IcecatParsingException, IOException{
		Map<Long, String> ids = new HashMap<Long, String>();
		ids.put(1L, "a Date");
		
		EasyMock.expect(parsingController.getAllProductsId()).andReturn(ids );
		EasyMock.replay(parsingController);
		
		Map<Long, String> idMap = icecatController.getAllProductsId();
		
		assertEquals("Maps were expected to be equal", ids, idMap);
		EasyMock.verify(parsingController);
	}
	
	@Test
	public void testGetProduct() throws IcecatConnectionException, IcecatParsingException {
		long id = 1L;
		Product aProduct = new Product();
		EasyMock.expect(parsingController.getIcecatProduct(id)).andReturn(aProduct );
		EasyMock.replay(parsingController);
		
		Product product = icecatController.getProduct(id);
		
		assertEquals("Products were expected to be equal", aProduct, product);
		EasyMock.verify(parsingController);
	}
	
	@Test
	public void testSaveProduct() {
		SimpleProduct simpleProduct = new SimpleProduct();
		databaseController.saveProduct(simpleProduct);
		
		icecatController.saveProduct(simpleProduct );
	}

}
