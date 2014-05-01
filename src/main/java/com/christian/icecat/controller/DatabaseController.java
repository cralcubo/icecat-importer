package com.christian.icecat.controller;

import com.christian.icecat.dao.HibernateConnector;
import com.christian.icecat.dao.ProductDAO;
import com.christian.icecat.dao.ProductDAOImpl;
import com.christian.icecat.model.entities.SimpleProduct;

/**
 * Class that controls all the actions
 * to be executed to a Database.
 *  
 * @author christian
 *
 */
public class DatabaseController {
	private ProductDAO dao;
	
	public DatabaseController(HibernateConnector databaseConnector){
		dao = new ProductDAOImpl(databaseConnector);
	}
	
	public void saveProduct(SimpleProduct product){
		dao.persist(product);
	}

}
