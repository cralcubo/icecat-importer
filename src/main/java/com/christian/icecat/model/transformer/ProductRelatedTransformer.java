package com.christian.icecat.model.transformer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.christian.icecat.exceptions.IcecatNullIdException;
import com.christian.icecat.model.entities.SimpleProductRelated;
import com.christian.icecat.model.product.ProductRelated;
import com.christian.icecat.utility.TransformerUtlity;

public class ProductRelatedTransformer implements
		Transformable<SimpleProductRelated, ProductRelated> {
	
	private final Logger LOG = LoggerFactory.getLogger(ProductRelatedTransformer.class);
	
	@Override
	public SimpleProductRelated transform(ProductRelated originalObject){
		SimpleProductRelated simpleObject = new SimpleProductRelated();
		
		if(originalObject != null){
			try {
				simpleObject.setId(TransformerUtlity.idChecker(originalObject.getID()));
				if(originalObject.getProduct().size() > 0){
					simpleObject.setProductAffiliatedId(TransformerUtlity.idChecker(originalObject.getProduct().get(0).getID()));
				}
			} catch (IcecatNullIdException e) {
				LOG.warn("{} has no ID, it wont be included.", this.getClass());
			}
		}
		
		return simpleObject;
	}

}
