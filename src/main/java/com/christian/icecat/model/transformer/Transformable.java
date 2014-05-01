package com.christian.icecat.model.transformer;

import com.christian.icecat.exceptions.IcecatNullIdException;

/**
 * Any class that wants to transform an
 * Icecat object to a Simple object needs
 * to implements this interface.
 * 
 * @author christian
 *
 * @param <N> This is the New <N> type of object that will result from the transformation.
 * @param <O> This is the Original <O> object that will be transformed. 
 */

public interface Transformable<N,O> {
	
	N transform(O originalObject) throws IcecatNullIdException;

}
