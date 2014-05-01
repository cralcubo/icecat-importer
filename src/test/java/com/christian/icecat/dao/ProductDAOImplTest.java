package com.christian.icecat.dao;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import com.christian.icecat.model.entities.SimpleProduct;

public class ProductDAOImplTest {
	private ProductDAOImpl dao;
	private HibernateConnector connector;
	
	@Before
	public void setUp(){
		connector = EasyMock.createMock(HibernateConnector.class);
		dao = new ProductDAOImpl(connector );
	}
	
	@Test
	public void persistTest() {
		SimpleProduct product = new SimpleProduct();
		connector.open();
		connector.persistObject(product);
		connector.close();
		EasyMock.replay(connector);
		
		dao.persist(product);
	}

}
