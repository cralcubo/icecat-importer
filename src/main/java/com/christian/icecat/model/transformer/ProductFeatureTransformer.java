package com.christian.icecat.model.transformer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.christian.icecat.exceptions.IcecatNullIdException;
import com.christian.icecat.model.entities.SimpleProductFeature;
import com.christian.icecat.model.product.ProductFeature;
import com.christian.icecat.utility.TransformerUtlity;

public class ProductFeatureTransformer implements
		Transformable<SimpleProductFeature, ProductFeature> {
	private final Logger LOG = LoggerFactory.getLogger(ProductFeatureTransformer.class);

	@Override
	public SimpleProductFeature transform(ProductFeature originalObject){
		SimpleProductFeature simpleObject = new SimpleProductFeature();
		
		if(originalObject != null){
			try {
				simpleObject.setId(TransformerUtlity.idChecker(originalObject.getID()));
				simpleObject.setPresentationValue(TransformerUtlity.bigStringVerifier(originalObject.getPresentationValue()));
				simpleObject.setFeatures(TransformerUtlity.loop(originalObject.getFeature(), new FeatureTransformer()));
			} catch (IcecatNullIdException e) {
				LOG.warn("{} has no ID, it wont be included.", this.getClass());
			}
		}
		
		return simpleObject;
	}

}
