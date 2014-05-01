package com.christian.icecat.utils;

import java.nio.file.Path;

import com.christian.icecat.connector.AbstractIcecatConnector;
import com.christian.icecat.connector.IcecatFileConnector;
import com.christian.icecat.exceptions.IcecatConnectionException;
import com.christian.icecat.exceptions.IcecatParsingException;
import com.christian.icecat.model.product.ICECATInterface;
import com.christian.icecat.model.product.Product;
import com.christian.icecat.parser.IcecatProductParser;

public class ProductRetriever {

	public static Product getProductFromFile(Path path){
		AbstractIcecatConnector connector = new IcecatFileConnector(path);
		IcecatProductParser parser = new IcecatProductParser(connector);
		ICECATInterface iceInterface;
		try {
			iceInterface = parser.parseStream(ICECATInterface.class);
		} catch (IcecatConnectionException | IcecatParsingException e) {
			e.printStackTrace();
			return null;
		}
		
		return iceInterface.getProduct();
	}

}
