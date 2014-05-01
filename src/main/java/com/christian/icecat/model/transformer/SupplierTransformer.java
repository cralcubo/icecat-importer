package com.christian.icecat.model.transformer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.christian.icecat.exceptions.IcecatNullIdException;
import com.christian.icecat.model.entities.SimpleSupplier;
import com.christian.icecat.model.product.Supplier;
import com.christian.icecat.utility.TransformerUtlity;

public class SupplierTransformer implements Transformable<SimpleSupplier, Supplier> {
	private final Logger LOG = LoggerFactory.getLogger(SupplierTransformer.class);

	@Override
	public SimpleSupplier transform(Supplier originalObject) {
		SimpleSupplier simpleSupplier = new SimpleSupplier();
		
		if(originalObject != null){
			try {
				simpleSupplier.setId(TransformerUtlity.idChecker(originalObject.getID()));
				simpleSupplier.setName(TransformerUtlity.bigStringVerifier(originalObject.getNameAttribute()));
			} catch (IcecatNullIdException e) {
				LOG.warn("{} has no ID, it wont be included.", this.getClass());
			}
		}
		
		return simpleSupplier;
	}

}
