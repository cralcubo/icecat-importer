package com.christian.icecat.importer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Map;
import java.util.TreeMap;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import com.christian.icecat.controller.IcecatControllerInterface;
import com.christian.icecat.exceptions.IcecatConnectionException;
import com.christian.icecat.exceptions.IcecatNullIdException;
import com.christian.icecat.exceptions.IcecatParsingException;
import com.christian.icecat.model.entities.SimpleProduct;
import com.christian.icecat.model.product.Product;
import com.christian.icecat.utility.TransformerUtlity;

public class IcecatProductsImporterTest {
	
	private Map<Long, String> mapId;
	private IcecatControllerInterface icecatController;
	
	private IcecatProductsImporter importer;
	
	@Before
	public void setUp() throws IcecatConnectionException, IcecatParsingException, IOException{
		mapId = new TreeMap<Long, String>();
		
		icecatController = EasyMock.createMock(IcecatControllerInterface.class);
		
		importer = new IcecatProductsImporter(icecatController, mapId );
	}
	
	@Test
	public void testComputeOne() throws IcecatConnectionException, IcecatParsingException, IcecatNullIdException{
		Long productId = 1L;
		String dateStr = "2005-10-06";
		mapId.put(productId , dateStr);
		
		Product product = new Product();
		product.setID(BigInteger.valueOf(productId));
		EasyMock.expect(icecatController.getProduct(productId)).andReturn(product);
		
		EasyMock.replay(icecatController);
		
		importer.compute();
		
		assertTrue("Just one product parsed was expected", importer.getProductsParsed().size() == 1); 
		
		SimpleProduct simpleProduct = importer.getProductsParsed().get(0);
		
		assertEquals("The update date was not set as expected", simpleProduct.getUpdateDate(), TransformerUtlity.parseDate(dateStr));
		
		EasyMock.verify(icecatController);
	}

}
