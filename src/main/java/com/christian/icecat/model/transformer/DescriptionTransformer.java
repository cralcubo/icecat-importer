package com.christian.icecat.model.transformer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.christian.icecat.exceptions.IcecatNullIdException;
import com.christian.icecat.model.entities.SimpleDescription;
import com.christian.icecat.model.product.ProductDescription;
import com.christian.icecat.utility.TransformerUtlity;

public class DescriptionTransformer implements Transformable<SimpleDescription, ProductDescription> {
	private final Logger LOG = LoggerFactory.getLogger(DescriptionTransformer.class);
	
	@Override
	public SimpleDescription transform(ProductDescription productDescription){
		SimpleDescription simpleDescription = new SimpleDescription();
		
		if(productDescription != null){
			try {
				simpleDescription.setId(TransformerUtlity.idChecker(productDescription.getID()));
				simpleDescription.setLongDescription(productDescription.getLongDesc());
				simpleDescription.setPdfUrl(TransformerUtlity.bigStringVerifier(productDescription.getPDFURL()));
				simpleDescription.setShortDescription(productDescription.getShortDesc());
				simpleDescription.setUrl(TransformerUtlity.bigStringVerifier(productDescription.getURL()));
			} catch (IcecatNullIdException e) {
				LOG.warn("{} has no ID, it wont be included.", this.getClass());
			}
		}
		
		return simpleDescription;
	}

}
