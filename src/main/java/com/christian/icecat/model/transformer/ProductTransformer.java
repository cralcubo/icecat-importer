package com.christian.icecat.model.transformer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.christian.icecat.exceptions.IcecatNullIdException;
import com.christian.icecat.model.entities.SimpleProduct;
import com.christian.icecat.model.product.Product;
import com.christian.icecat.utility.TransformerUtlity;

public class ProductTransformer implements Transformable<SimpleProduct, Product> {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Override
	public SimpleProduct transform(Product product) throws IcecatNullIdException {
		SimpleProduct simpleProduct = new SimpleProduct();
		
		if(product != null){
			simpleProduct.setId(TransformerUtlity.idChecker(product.getID()));
			simpleProduct.setHighPic(TransformerUtlity.bigStringVerifier(product.getHighPic()));
			simpleProduct.setName(TransformerUtlity.bigStringVerifier(product.getName()));
			simpleProduct.setQuality(TransformerUtlity.bigStringVerifier(product.getQuality()));
			simpleProduct.setTitle(TransformerUtlity.bigStringVerifier(product.getTitle()));
			simpleProduct.setReleaseDate(TransformerUtlity.parseDate(product.getReleaseDate()));
			
			LOG.debug("Transforming {}: {}",simpleProduct.getName(), simpleProduct.getId());
			
			simpleProduct.setCategories(TransformerUtlity.loop(product.getCategory(), new CategoryTransformer()));
			simpleProduct.setDescriptions(TransformerUtlity.loop(product.getProductDescription(), new DescriptionTransformer()));
			simpleProduct.setCategoryFeatureGroups(TransformerUtlity.loop(product.getCategoryFeatureGroup(), new CartegoryFeatureGroupTransformer()));
			simpleProduct.setProductFeatures(TransformerUtlity.loop(product.getProductFeature(), new ProductFeatureTransformer()));
			simpleProduct.setProductsRelated(TransformerUtlity.loop(product.getProductRelated(), new ProductRelatedTransformer()));
			
			simpleProduct.setSupplier(new SupplierTransformer().transform(product.getSupplier()));
		}
		
		return simpleProduct;
	}

}
