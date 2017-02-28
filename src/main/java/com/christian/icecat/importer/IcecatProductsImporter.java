package com.christian.icecat.importer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.RecursiveAction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.christian.icecat.controller.IcecatControllerInterface;
import com.christian.icecat.exceptions.IcecatConnectionException;
import com.christian.icecat.exceptions.IcecatNullIdException;
import com.christian.icecat.exceptions.IcecatParsingException;
import com.christian.icecat.model.entities.SimpleProduct;
import com.christian.icecat.model.product.Product;
import com.christian.icecat.model.transformer.ProductTransformer;
import com.christian.icecat.utility.TransformerUtlity;

/**
 * Class that uses the Fork-Join framework to distribute the importing +
 * parsing of icecat products.
 * 
 * All this parsed products are stored in a List of SimpleProducts.
 * 
 * @author christian
 *
 */
public class IcecatProductsImporter extends RecursiveAction {
	private static final Logger LOG = LoggerFactory
			.getLogger(IcecatProductsImporter.class);

	private static final long serialVersionUID = 187687623L;

	private IcecatControllerInterface icecatController;

	private Map<Long, String> productsIdMap;

	private int resizeDimension;

	private List<SimpleProduct> productsParsed;

	public IcecatProductsImporter(IcecatControllerInterface icecatController){
		this(icecatController, null);
	}

	public IcecatProductsImporter(IcecatControllerInterface icecatController,
			Map<Long, String> productsId) {
		this.icecatController = icecatController;

		if (productsId == null) {
			try {
				productsId = icecatController.getAllProductsId();
			} catch (IcecatConnectionException | IcecatParsingException e) {
				LOG.error("No products id could be retrieved from Icecat, therefore is not possible to import any.", e);
				throw new RuntimeException(e);
			}
		}
		this.setProductsIdMap(productsId);

		this.setProductsParsed(new ArrayList<SimpleProduct>());
	}
	
	/**
	 * Method that will split the importing and parsing 
	 * of an Icecat product by half of the number of products
	 * ID that are required to be imported.
	 *  
	 */
	@Override
	protected void compute() {
		// Convert Map to a TreeMap to divide it later
		TreeMap<Long, String> sortedMap = new TreeMap<>(getProductsIdMap());

		// Check number of products to import
		if (getResizeDimension() > 0 && getResizeDimension() < sortedMap.size()) {
			Long lastKey = keyFinder(sortedMap, getResizeDimension());
			sortedMap = new TreeMap<>(sortedMap.subMap(sortedMap.firstKey(),
					lastKey));
		}

		int mapSize = sortedMap.size();

		if (mapSize == 1) {
			//Do the actual import and parsing
			parseAndStoreProduct(sortedMap.firstKey());
		} else {
			// Divide the size in 2 to create two workers
			int dividedSize = mapSize / 2;

			// Create sub maps
			Long halfKey = keyFinder(sortedMap, dividedSize);
			SortedMap<Long, String> subMapA = sortedMap.subMap(sortedMap.firstKey(), halfKey);
			SortedMap<Long, String> subMapB = sortedMap.tailMap(halfKey);
			
			IcecatProductsImporter worker1 = new IcecatProductsImporter(icecatController, subMapA);
			IcecatProductsImporter worker2 = new IcecatProductsImporter(icecatController, subMapB);
			
			invokeAll(worker1, worker2);
			
			productsParsed.addAll(worker1.getProductsParsed());
			productsParsed.addAll(worker2.getProductsParsed());
		}

	}

	private void parseAndStoreProduct(Long productId) {
		LOG.debug("Thread: {} fetching product: {}", Thread.currentThread(), productId);
		SimpleProduct simpleProduct;
		Product aProduct;
		try {
			aProduct = icecatController.getProduct(productId);
			ProductTransformer transformer = new ProductTransformer();
			simpleProduct = transformer.transform(aProduct);
			// Set the update date for this product
			String updateStr = getProductsIdMap().get(productId);
			simpleProduct.setUpdateDate(TransformerUtlity.parseDate(updateStr));

			// Save product in List
			//V1.0 Note: Just for this version we will store
			//products in a list to be persisted later, next release
			//implement a live queue of objects that can be persisted
			//at the same time as they are imported from icecat.
			productsParsed.add(simpleProduct);
			
		} catch (IcecatConnectionException e) {
			LOG.error("This product: {} strangely is not available in ICECAT, sometimes this happens because a product can be available in one language but not in the one that you choose. This product will not be included.", productId, e);
		} catch (IcecatParsingException e) {
			LOG.error("There was an error parsing the product: {}.", productId, e);
		} catch (IcecatNullIdException e) {
			LOG.error("Something really weird happen with this product: {}; this product will not be parsed.", productId, e);
		}

	}

	private Long keyFinder(Map<Long, String> map, int position) {
		int counter = 0;
		for (Long id : map.keySet()) {
			if (counter == position) {
				return id;
			}
			counter++;
		}
		throw new IllegalArgumentException(String.format(
				"Key not found in this position %d", position));
	}

	public Map<Long, String> getProductsIdMap() {
		return productsIdMap;
	}

	public void setProductsIdMap(Map<Long, String> productsIdMap) {
		this.productsIdMap = productsIdMap;
	}

	public int getResizeDimension() {
		return resizeDimension;
	}

	public void setResizeDimension(int resizeDimension) {
		this.resizeDimension = resizeDimension;
	}

	public List<SimpleProduct> getProductsParsed() {
		return productsParsed;
	}

	public void setProductsParsed(List<SimpleProduct> productsParsed) {
		this.productsParsed = productsParsed;
	}

}
