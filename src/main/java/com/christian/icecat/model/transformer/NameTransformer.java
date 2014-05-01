package com.christian.icecat.model.transformer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.christian.icecat.exceptions.IcecatNullIdException;
import com.christian.icecat.model.entities.SimpleName;
import com.christian.icecat.model.product.Name;
import com.christian.icecat.utility.TransformerUtlity;

public class NameTransformer implements Transformable<SimpleName, Name> {
	private final Logger LOG = LoggerFactory.getLogger(NameTransformer.class);

	@Override
	public SimpleName transform(Name name) {
		SimpleName simpleName = new SimpleName();
		
		if(name != null){
			try {
				simpleName.setId(TransformerUtlity.idChecker(name.getID()));
				simpleName.setValue(name.getValueAttribute());
			} catch (IcecatNullIdException e) {
				LOG.warn("{} has no ID, it wont be included.", this.getClass());
			}
		}
		
		
		return simpleName;
	}

}
