package com.christian.icecat.integration;

import java.util.concurrent.ForkJoinPool;

import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.christian.icecat.connector.AbstractIcecatConnector;
import com.christian.icecat.connector.IcecatURLConnector;
import com.christian.icecat.controller.DatabaseController;
import com.christian.icecat.controller.IcecatController;
import com.christian.icecat.controller.IcecatControllerInterface;
import com.christian.icecat.controller.ParsingController;
import com.christian.icecat.dao.HibernateConnector;
import com.christian.icecat.importer.IcecatProductsImporter;
import com.christian.icecat.model.entities.SimpleProduct;

public class IcecatImporterIntegrationTest {
	private final static Logger LOG = LoggerFactory.getLogger(IcecatImporterIntegrationTest.class);
	
	private final static String USER = "a user";
	private final static String PWD = "a pwd";
	private final static String ICECAT_URL = "https://data.icecat.biz/export/freexml/NL/";
	private static final String DB_USER = "a user";
	private static final String DB_PWD = "a pwd";
	private static final String DB_URL = "localhost";
	private static final String DB_PORT = "3306";
	private static final String DB_NAME = "icecat";
	
	private static final int NR_PRODUCTS = 2;
	
	@Test
	@Ignore
	public void testIcecatImporter() {
		AbstractIcecatConnector connector = new IcecatURLConnector(USER, PWD, ICECAT_URL);
		HibernateConnector databaseConnector = new HibernateConnector(DB_USER, DB_PWD, DB_URL, DB_PORT, DB_NAME);
		
		ParsingController parsingController = new ParsingController(connector);
		DatabaseController databaseController = new DatabaseController(databaseConnector);
		IcecatControllerInterface icecatController = new IcecatController(parsingController, databaseController);

		IcecatProductsImporter importer;
		importer = new IcecatProductsImporter(icecatController);
		//How many products will be imported?
		importer.setResizeDimension(NR_PRODUCTS);

		// create thread pool!
		int nrThreads = Runtime.getRuntime().availableProcessors();
		LOG.info("Number of threads {}", nrThreads);
		
		ForkJoinPool pool = new ForkJoinPool(nrThreads);
		LOG.info("Created Pool {}", pool);
		LOG.info("Start importing");
		pool.invoke(importer);
		
		//once all the products are parsed lets persist them
		LOG.info("Products parsed: {}", importer.getProductsParsed().size());
		for(SimpleProduct product : importer.getProductsParsed()){
			LOG.info("Saving product {} to Database", product.getId());
			icecatController.saveProduct(product);
		}
	}

}
