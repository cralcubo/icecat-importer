package com.christian.icecat.transformer;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.nio.file.Paths;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.christian.icecat.exceptions.IcecatConnectionException;
import com.christian.icecat.exceptions.IcecatParsingException;
import com.christian.icecat.exceptions.IcecatNullIdException;
import com.christian.icecat.model.entities.SimpleCategory;
import com.christian.icecat.model.entities.SimpleCategoryFeatureGroup;
import com.christian.icecat.model.entities.SimpleDescription;
import com.christian.icecat.model.entities.SimpleFeature;
import com.christian.icecat.model.entities.SimpleFeatureGroup;
import com.christian.icecat.model.entities.SimpleName;
import com.christian.icecat.model.entities.SimpleProduct;
import com.christian.icecat.model.entities.SimpleProductFeature;
import com.christian.icecat.model.entities.SimpleProductRelated;
import com.christian.icecat.model.entities.SimpleSupplier;
import com.christian.icecat.model.product.Product;
import com.christian.icecat.model.transformer.ProductTransformer;
import com.christian.icecat.utility.TransformerUtlity;
import com.christian.icecat.utils.ProductRetriever;

public class ProductTransforemerTest {
	protected final Logger LOG = LoggerFactory.getLogger(ProductTransforemerTest.class);
	private ProductTransformer transformer;
	private final String PATH = "./src/test/resources/small-product.xml";
	
	@Before
	public void setUp(){
		transformer = new ProductTransformer();
	}
	
	@Test(expected=IcecatNullIdException.class)
	public void testProductWithNoId() throws IcecatNullIdException{
		Product p = new Product();
		transformer.transform(p);
	}
	
	@Test
	public void testParseDate(){
		String dateStr1 = "20140427113722";
		String dateStr2 = "2005-10-06";
		
		Date date1 = TransformerUtlity.parseDate(dateStr1);
		Date date2 = TransformerUtlity.parseDate(dateStr2);
		
		assertNotNull("Date was supposed to be parsed", date1);
		assertNotNull("Date was supposed to be parsed", date2);
		
		LOG.debug("Date: {} was parsed to: {}",dateStr1, date1.toString());
		LOG.debug("Date: {} was parsed to: {}",dateStr2, date2.toString());
			
	}
	
	@Test
	public void testFullTransformation() throws IcecatConnectionException, IcecatParsingException, IcecatNullIdException{
		Product product = ProductRetriever.getProductFromFile(Paths.get(PATH));
		assertNotNull("The class ProductRetriever did not parse the xml file to generate a product",product);
		SimpleProduct simpleProduct = transformer.transform(product);
		assertNotNull(simpleProduct);
		
		loggerAsserter("Pic", simpleProduct.getHighPic());
		loggerAsserter("Name", simpleProduct.getName());
		loggerAsserter("Title", simpleProduct.getTitle());
		loggerAsserter("Prod ID", String.valueOf(simpleProduct.getId()));
		loggerAsserter("Date", simpleProduct.getReleaseDate().toString());
		loggerAsserter("Quality", simpleProduct.getQuality());
		
		for(SimpleCategory cat : simpleProduct.getCategories()){
			loggerAsserter("Cat ID", String.valueOf(cat.getId()));
			for(SimpleName name : cat.getNames()){
				loggerAsserter("Name value", name.getValue());
				loggerAsserter("Name ID", String.valueOf(name.getId()));
			}
		}
		
		for(SimpleDescription desc : simpleProduct.getDescriptions()){
			loggerAsserter("Desc ID", String.valueOf(desc.getId()));
			loggerAsserter("Long Desc", desc.getLongDescription());
			loggerAsserter("Short Desc", desc.getShortDescription());
			loggerAsserter("PDF URL", desc.getPdfUrl());
			loggerAsserter("Url", desc.getUrl());
		}
		
		for(SimpleCategoryFeatureGroup scfg : simpleProduct.getCategoryFeatureGroups()){
			loggerAsserter("Feature Cat Group ID", String.valueOf(scfg.getId()));
			for(SimpleFeatureGroup sfg : scfg.getFeatureGroups()){
				loggerAsserter("Feature Group ID", String.valueOf(sfg.getId()));
				for(SimpleName name : sfg.getNames()){
					loggerAsserter("Name value", name.getValue());
					loggerAsserter("Name ID", String.valueOf(name.getId()));
				}
			}
		}
		
		for(SimpleProductFeature prodFeature : simpleProduct.getProductFeatures()){
			loggerAsserter("Prod Feat ID", String.valueOf(prodFeature.getId()));
			loggerAsserter("Presentation val", prodFeature.getPresentationValue());
			
			for(SimpleFeature feature : prodFeature.getFeatures()){
				loggerAsserter("Feature ID", String.valueOf(feature.getId()));
				for(SimpleName name : feature.getNames()){
					loggerAsserter("Name value", name.getValue());
					loggerAsserter("Name ID", String.valueOf(name.getId()));
				}
				
			}
		}
		
		for(SimpleProductRelated prodRel : simpleProduct.getProductsRelated()){
			loggerAsserter("Prod Related ID", String.valueOf(prodRel.getId()));
			loggerAsserter("Prod Affiliated ID", String.valueOf(prodRel.getProductAffiliatedId()));
		}
		
		SimpleSupplier supplier = simpleProduct.getSupplier();
		
		loggerAsserter("Supplier ID", String.valueOf(supplier.getId()));
		loggerAsserter("Supplier Name", supplier.getName());
		
	}
	
	private void loggerAsserter(String label, String variable){
		LOG.info("{}: {}",label,variable);
		assertFalse(variable.isEmpty());
	}

}
