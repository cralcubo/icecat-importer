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
@Table(name = "CategoryFeatureGroup")
public class SimpleCategoryFeatureGroup {
	
	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "categoryFeatureGroups")
	private Set<SimpleProduct> products;
	
	@Id
	@Column(name = "categoryFeatureGroup_id")
	private long id;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(joinColumns = {@JoinColumn(name = "categoryFeatureGroup_id")}, inverseJoinColumns = {@JoinColumn(name = "featureGroup_id")})
	private Set<SimpleFeatureGroup> featureGroups;

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

	public Set<SimpleFeatureGroup> getFeatureGroups() {
		return featureGroups;
	}

	public void setFeatureGroups(Set<SimpleFeatureGroup> featureGroups) {
		this.featureGroups = featureGroups;
	}
	
	

}
