package com.christian.icecat.integration;

import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.christian.icecat.dao.HibernateConnector;
import com.christian.icecat.dao.ProductDAOImpl;
import com.christian.icecat.exceptions.IcecatNullIdException;
import com.christian.icecat.model.entities.SimpleProduct;
import com.christian.icecat.model.product.Product;
import com.christian.icecat.model.transformer.ProductTransformer;
import com.christian.icecat.utils.ProductRetriever;

public class ProductDAOImplIntegrationTest {
	private static final String PATH_B = "./src/test/resources/product-1502.xml";
	private final Logger log = LoggerFactory.getLogger(getClass());
	private ProductDAOImpl productDao;
	private final String PATH = "./src/test/resources/product-8046.xml"; 
	
	@Before
	public void setUp() {
		String user = "a user";
		String password = "a pwd";
		String dbUrl = "localhost";
		String dbPort = "3306";
		String dbName = "hibernate_test";
		
		productDao = new ProductDAOImpl(new HibernateConnector(user, password, dbUrl, dbPort, dbName));
	}
	
	@Test
	@Ignore
	public void testPersist(){
		Product product = ProductRetriever.getProductFromFile(Paths.get(PATH));
		Product productB = ProductRetriever.getProductFromFile(Paths.get(PATH_B));
		SimpleProduct simpleProduct;
		SimpleProduct simpleProductB;
		try {
			simpleProduct = new ProductTransformer().transform(product);
			log.info(".:. Persisting productA: {}::{}", simpleProduct.getName(), simpleProduct.getId());
			
			productDao.persist(simpleProduct);
			log.info(".:. Product stored in Database");
			
			simpleProductB = new ProductTransformer().transform(productB);
			log.info(".:. Persisting productB: {}::{}", simpleProductB.getName(), simpleProductB.getId());
			productDao.persist(simpleProductB);
			log.info(".:. ProductB stored in Database");
			
		} catch (IcecatNullIdException e) {
			e.printStackTrace();
			log.error("There was an object with no ID");
		}
		
		
		
	}

}
