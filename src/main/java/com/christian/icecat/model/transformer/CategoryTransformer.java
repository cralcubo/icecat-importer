package com.christian.icecat.model.transformer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.christian.icecat.exceptions.IcecatNullIdException;
import com.christian.icecat.model.entities.SimpleCategory;
import com.christian.icecat.model.product.Category;
import com.christian.icecat.utility.TransformerUtlity;

public class CategoryTransformer implements Transformable<SimpleCategory, Category> {
	private final Logger LOG = LoggerFactory.getLogger(CategoryTransformer.class);

	@Override
	public SimpleCategory transform(Category category){
		SimpleCategory simpleCategory = new SimpleCategory();
		
		if(category != null){
			try {
				simpleCategory.setId(TransformerUtlity.idChecker(category.getID()));
				simpleCategory.setNames(TransformerUtlity.loop(category.getName(), new NameTransformer()));
			} catch (IcecatNullIdException e) {
				LOG.warn("{} has no ID, it wont be included.", this.getClass());
			}
		}
		
		return simpleCategory;
	}

}
