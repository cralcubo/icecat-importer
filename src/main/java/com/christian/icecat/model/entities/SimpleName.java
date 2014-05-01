package com.christian.icecat.model.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name="Name")
public class SimpleName {
	
	@ManyToMany(mappedBy = "names", cascade = CascadeType.ALL)
	private Set<SimpleFeature> features;
	
	@ManyToMany(mappedBy = "names", cascade = CascadeType.ALL)
	private Set<SimpleFeatureGroup> featureGroups;
	
	@ManyToMany(mappedBy ="names", cascade = CascadeType.ALL)
	private Set<SimpleCategory> categories;
	
    @Id
    @Column(name="name_id")
    private long id;
    
    @Column(columnDefinition = "TEXT")
    private String value;

	public Set<SimpleCategory> getCategories() {
		if(categories == null){
			categories = new HashSet<>();
		}
		return categories;
	}

	public void setCategories(Set<SimpleCategory> categories) {
		this.categories = categories;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Set<SimpleFeatureGroup> getFeatureGroups() {
		return featureGroups;
	}

	public void setFeatureGroups(Set<SimpleFeatureGroup> featureGroups) {
		this.featureGroups = featureGroups;
	}

	public Set<SimpleFeature> getFeatures() {
		return features;
	}

	public void setFeatures(Set<SimpleFeature> features) {
		this.features = features;
	}

	

}
