package com.christian.icecat.utility;

import com.christian.icecat.model.entities.SimpleProduct;

public class ProductUtility {
	
	public static void setUpdateDate(SimpleProduct product, String date){
		product.setUpdateDate(TransformerUtlity.parseDate(date));
	}

}
