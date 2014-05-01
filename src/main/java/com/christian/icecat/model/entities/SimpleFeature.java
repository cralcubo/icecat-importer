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
@Table(name = "Feature")
public class SimpleFeature {
	
	@ManyToMany(mappedBy = "features", cascade = CascadeType.ALL)
	private Set<SimpleProductFeature> productFeatures;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(joinColumns = {@JoinColumn(name = "feature_id")}, inverseJoinColumns = {@JoinColumn(name = "name_id")})
	private Set<SimpleName> names;
	
	@Id
	@Column(name = "feature_id")
	private long id;

	public Set<SimpleProductFeature> getProductFeatures() {
		return productFeatures;
	}

	public void setProductFeatures(Set<SimpleProductFeature> productFeatures) {
		this.productFeatures = productFeatures;
	}

	public Set<SimpleName> getNames() {
		return names;
	}

	public void setNames(Set<SimpleName> names) {
		this.names = names;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	
	
}
