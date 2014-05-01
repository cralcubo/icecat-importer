package com.christian.icecat.model.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ProductFeature")
public class SimpleProductFeature {
	@ManyToMany(mappedBy = "productFeatures", cascade = CascadeType.ALL)
	private Set<SimpleProduct> products;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(joinColumns = {@JoinColumn(name = "productFeature_id")}, inverseJoinColumns = {@JoinColumn(name = "feature_id")})
	private Set<SimpleFeature> features;
	
	@Id
	@Column(name = "productFeature_id")
	private long id;
	
	@Column(columnDefinition = "TEXT")
	private String presentationValue;

	public Set<SimpleProduct> getProducts() {
		return products;
	}

	public void setProducts(Set<SimpleProduct> products) {
		this.products = products;
	}

	public Set<SimpleFeature> getFeatures() {
		return features;
	}

	public void setFeatures(Set<SimpleFeature> features) {
		this.features = features;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPresentationValue() {
		return presentationValue;
	}

	public void setPresentationValue(String presentationValue) {
		this.presentationValue = presentationValue;
	}
	
	

}
