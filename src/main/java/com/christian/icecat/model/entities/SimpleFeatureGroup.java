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
@Table(name = "FeatureGroup")
public class SimpleFeatureGroup {
	@ManyToMany(mappedBy = "featureGroups", cascade = CascadeType.ALL)
	private Set<SimpleCategoryFeatureGroup> categoryFeatureGroups;
	
	@Id
	@Column(name = "featureGroup_id")
	private long id;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(joinColumns = {@JoinColumn(name = "featureGroup_id")}, inverseJoinColumns = {@JoinColumn(name = "name_id")})
	private Set<SimpleName> names;

	public Set<SimpleCategoryFeatureGroup> getCategoryFeatureGroups() {
		return categoryFeatureGroups;
	}

	public void setCategoryFeatureGroups(
			Set<SimpleCategoryFeatureGroup> categoryFeatureGroups) {
		this.categoryFeatureGroups = categoryFeatureGroups;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Set<SimpleName> getNames() {
		return names;
	}

	public void setNames(Set<SimpleName> names) {
		this.names = names;
	}
	
	

}
