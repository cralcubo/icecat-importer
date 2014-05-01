package com.christian.icecat.controller;

import java.util.Map;

import com.christian.icecat.exceptions.IcecatConnectionException;
import com.christian.icecat.exceptions.IcecatParsingException;
import com.christian.icecat.model.entities.SimpleProduct;
import com.christian.icecat.model.product.Product;

public interface IcecatControllerInterface {
	
	public Map<Long, String> getAllProductsId() throws IcecatConnectionException, IcecatParsingException;
	
	public Product getProduct(long id) throws IcecatConnectionException, IcecatParsingException;
	
	public void saveProduct(SimpleProduct product);

}
