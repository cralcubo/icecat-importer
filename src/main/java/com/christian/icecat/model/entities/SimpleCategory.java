package com.christian.icecat.model.entities;

import java.util.HashSet;
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
@Table(name="Category")
public class SimpleCategory  {
	@ManyToMany(mappedBy = "categories", cascade=CascadeType.ALL)
	private Set<SimpleProduct> products;
	
	@ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(joinColumns = {@JoinColumn(name = "category_id")}, inverseJoinColumns = {@JoinColumn(name="name_id")})
    private Set<SimpleName> names;
	
	@Id
    @Column(name="category_id")
    private long id;

	public Set<SimpleProduct> getProducts() {
		if(products == null){
			products = new HashSet<>();
		}
		return products;
	}

	public void setProducts(Set<SimpleProduct> products) {
		this.products = products;
	}

	public Set<SimpleName> getNames() {
		if(names == null){
			names = new HashSet<>();
		}
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
