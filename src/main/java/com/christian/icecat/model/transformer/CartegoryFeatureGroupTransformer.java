package com.christian.icecat.model.transformer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.christian.icecat.exceptions.IcecatNullIdException;
import com.christian.icecat.model.entities.SimpleCategoryFeatureGroup;
import com.christian.icecat.model.product.CategoryFeatureGroup;
import com.christian.icecat.utility.TransformerUtlity;

public class CartegoryFeatureGroupTransformer implements
		Transformable<SimpleCategoryFeatureGroup, CategoryFeatureGroup> {
	private final Logger LOG = LoggerFactory.getLogger(CartegoryFeatureGroupTransformer.class);

	@Override
	public SimpleCategoryFeatureGroup transform(CategoryFeatureGroup categoryFeatureGroup){
		SimpleCategoryFeatureGroup simpleObject = new SimpleCategoryFeatureGroup();
		
		if(simpleObject != null){
			try {
				simpleObject.setId(TransformerUtlity.idChecker(categoryFeatureGroup.getID()));
				simpleObject.setFeatureGroups(TransformerUtlity.loop(categoryFeatureGroup.getFeatureGroupElement(), new FeatureGroupTransformer()));
			} catch (IcecatNullIdException e) {
				LOG.warn("{} has no ID, it wont be included.", this.getClass());
			}
		}
		
		return simpleObject;
	}

}
