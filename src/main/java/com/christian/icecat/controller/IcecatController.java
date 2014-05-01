package com.christian.icecat.controller;

import java.util.Map;

import com.christian.icecat.exceptions.IcecatConnectionException;
import com.christian.icecat.exceptions.IcecatParsingException;
import com.christian.icecat.model.entities.SimpleProduct;
import com.christian.icecat.model.product.Product;

/**
 * Class that controls all the actions that can be
 * executed against Icecat.
 * 
 * @author christian
 *
 */
public class IcecatController implements IcecatControllerInterface {
	private ParsingController parsingController;
	private DatabaseController databaseController;
	
	public IcecatController(ParsingController parsingController,
			DatabaseController databaseController) {
		this.parsingController = parsingController;
		this.databaseController = databaseController;
	}
	
	/**
	 * Get all the products available from Icecat
	 * 
	 */
	public Map<Long, String> getAllProductsId() throws IcecatConnectionException, IcecatParsingException{
		return parsingController.getAllProductsId();
	}
	
	/**
	 * Get a product from icecat by its ID
	 * 
	 */
	public Product getProduct(long id) throws IcecatConnectionException, IcecatParsingException{
		return parsingController.getIcecatProduct(id);
	}
	
	/**
	 * Save a product to the Database
	 * 
	 */
	public void saveProduct(SimpleProduct product){
		databaseController.saveProduct(product);
	}

}
