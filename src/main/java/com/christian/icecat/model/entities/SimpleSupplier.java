package com.christian.icecat.model.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Supplier")
public class SimpleSupplier {
	
	@Id
	@Column(name = "supplier_id")
	private long id;
	
	@OneToMany(mappedBy = "supplier")
	private Set<SimpleProduct> products;
	
	@Column
	private String name;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Set<SimpleProduct> getProducts() {
		return products;
	}

	public void setProducts(Set<SimpleProduct> products) {
		this.products = products;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
