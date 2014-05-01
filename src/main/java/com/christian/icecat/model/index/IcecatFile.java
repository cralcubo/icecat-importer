package com.christian.icecat.model.index;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Class that contain all the 
 * information of an icecat-file.
 * To retrieve the information of a 
 * specific product the productId will be used.
 * 
 * @author christian
 *
 */
@XmlRootElement(name="file")
public class IcecatFile {
	private long productId;
	private String update;
	private String quality;
	private String onMarket;
	
	@XmlAttribute(name = "Product_ID")
	public long getProductId() {
		return productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	
	@XmlAttribute(name = "Updated")
	public String getUpdate() {
		return update;
	}
	public void setUpdate(String update) {
		this.update = update;
	}
	
	@XmlAttribute(name = "Quality")
	public String getQuality() {
		return quality;
	}
	public void setQuality(String quality) {
		this.quality = quality;
	}
	
	@XmlAttribute(name = "On_Market")
	public String getOnMarket() {
		return onMarket;
	}
	public void setOnMarket(String onMarket) {
		this.onMarket = onMarket;
	}
	
	@Override
	public String toString() {
		return String.format("Prod-ID: %s \nUpdate: %s\nQuality: %s",  productId, update, quality);
	}

}
