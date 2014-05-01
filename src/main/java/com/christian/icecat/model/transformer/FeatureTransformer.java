package com.christian.icecat.model.transformer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.christian.icecat.exceptions.IcecatNullIdException;
import com.christian.icecat.model.entities.SimpleFeature;
import com.christian.icecat.model.product.Feature;
import com.christian.icecat.utility.TransformerUtlity;

public class FeatureTransformer implements Transformable<SimpleFeature, Feature> {
	private final Logger LOG = LoggerFactory.getLogger(FeatureTransformer.class);

	@Override
	public SimpleFeature transform(Feature originalObject){
		SimpleFeature simpleFeature = new SimpleFeature();
		
		if(originalObject != null){
			try {
				simpleFeature.setId(TransformerUtlity.idChecker(originalObject.getID()));
				simpleFeature.setNames(TransformerUtlity.loop(originalObject.getName(), new NameTransformer()));
			} catch (IcecatNullIdException e) {
				LOG.warn("{} has no ID, it wont be included.", this.getClass());
			}
		}
		
		return simpleFeature;
	}

}
