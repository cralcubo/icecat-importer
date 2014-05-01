package com.christian.icecat.model.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ProductRelated")
public class SimpleProductRelated {
	@ManyToMany(mappedBy = "productsRelated", cascade = CascadeType.ALL)
	private Set<SimpleProduct> products;
	
	@Id
	@Column(name = "productRelated_id")
	private long id;
	
	@Column
	private long productAffiliatedId;

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

	public long getProductAffiliatedId() {
		return productAffiliatedId;
	}

	public void setProductAffiliatedId(long productAffiliatedId) {
		this.productAffiliatedId = productAffiliatedId;
	}
	

}
