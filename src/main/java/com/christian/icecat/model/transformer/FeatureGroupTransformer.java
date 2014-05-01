package com.christian.icecat.model.transformer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.christian.icecat.exceptions.IcecatNullIdException;
import com.christian.icecat.model.entities.SimpleFeatureGroup;
import com.christian.icecat.model.product.FeatureGroup;
import com.christian.icecat.utility.TransformerUtlity;

public class FeatureGroupTransformer implements
		Transformable<SimpleFeatureGroup, FeatureGroup> {
	private final Logger LOG = LoggerFactory.getLogger(FeatureGroupTransformer.class);

	@Override
	public SimpleFeatureGroup transform(FeatureGroup featureGroup) {
		SimpleFeatureGroup simpleObject = new SimpleFeatureGroup();
		
		if(featureGroup != null){
			try {
				simpleObject.setId(TransformerUtlity.idChecker(featureGroup.getID()));
				simpleObject.setNames(TransformerUtlity.loop(featureGroup.getName(), new NameTransformer()));
			} catch (IcecatNullIdException e) {
				LOG.warn("{} has no ID, it wont be included.", this.getClass());
			}
		}
		
		return simpleObject;
	}

}
