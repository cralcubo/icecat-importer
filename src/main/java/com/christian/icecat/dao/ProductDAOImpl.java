package com.christian.icecat.dao;

import com.christian.icecat.model.entities.SimpleProduct;

public  class ProductDAOImpl implements ProductDAO {
	
	private HibernateConnector connector;
	
	public ProductDAOImpl(HibernateConnector connector) {
		this.connector = connector;
	}

	@Override
	public void persist(SimpleProduct simpleProduct){
		connector.open();
		connector.persistObject(simpleProduct);
		connector.close();
	}
	

}
