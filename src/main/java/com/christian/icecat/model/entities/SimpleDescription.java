package com.christian.icecat.model.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ProductDescription")
public class SimpleDescription {
	@ManyToMany(mappedBy = "descriptions", cascade = CascadeType.ALL)
	private Set<SimpleProduct> products;
	
	@Id
	@Column(name="description_id")
	private long id;
	
	@Column(columnDefinition = "TEXT")
	private String longDescription;
	
	@Column(columnDefinition = "TEXT")
	private String shortDescription;
	
	@Column
	private String url;
	
	@Column
	private String pdfUrl;

	public Set<SimpleProduct> getProducts() {
		return products;
	}

	public void setProducts(Set<SimpleProduct> products) {
		this.products = products;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLongDescription() {
		return longDescription;
	}

	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPdfUrl() {
		return pdfUrl;
	}

	public void setPdfUrl(String pdfUrl) {
		this.pdfUrl = pdfUrl;
	}
	
	

}
