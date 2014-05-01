package com.christian.icecat.model.entities;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Product")
public class SimpleProduct{
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(joinColumns = {@JoinColumn(name = "product_id")}, inverseJoinColumns = {@JoinColumn(name = "category_id")})
    private Set<SimpleCategory> categories;
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(joinColumns = {@JoinColumn(name = "product_id")}, inverseJoinColumns = {@JoinColumn(name = "description_id")})
	private Set<SimpleDescription> descriptions;
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(joinColumns = {@JoinColumn(name = "product_id")}, inverseJoinColumns = {@JoinColumn(name = "categoryFeatureGroup_id")})
	private Set<SimpleCategoryFeatureGroup> categoryFeatureGroups;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(joinColumns = {@JoinColumn(name = "product_id")}, inverseJoinColumns = {@JoinColumn(name = "productFeature_id")})
	private Set<SimpleProductFeature> productFeatures;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(joinColumns = {@JoinColumn(name = "product_id")}, inverseJoinColumns = {@JoinColumn(name = "productRelated_id")})
	private Set<SimpleProductRelated> productsRelated;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "supplier_id")
    private SimpleSupplier supplier;
	
    @Id
    @Column(name="product_id")
    private long id;
    
	@Column
    private String highPic;
    
    @Column
    private String name;
    
    @Column
    private String quality;
    
    @Column 
    private String title;
    
    @Column
    private Date releaseDate;
    
    @Column 
    private Date updateDate;
    
    
	public String getHighPic() {
		return highPic;
	}

	public void setHighPic(String highPic) {
		this.highPic = highPic;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getQuality() {
		return quality;
	}

	public void setQuality(String quality) {
		this.quality = quality;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

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

	public Set<SimpleDescription> getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(Set<SimpleDescription> descriptions) {
		this.descriptions = descriptions;
	}

	public Set<SimpleCategoryFeatureGroup> getCategoryFeatureGroups() {
		return categoryFeatureGroups;
	}

	public void setCategoryFeatureGroups(Set<SimpleCategoryFeatureGroup> categoryFeatureGroups) {
		this.categoryFeatureGroups = categoryFeatureGroups;
	}

	public Set<SimpleProductFeature> getProductFeatures() {
		return productFeatures;
	}

	public void setProductFeatures(Set<SimpleProductFeature> productFeatures) {
		this.productFeatures = productFeatures;
	}

	public Set<SimpleProductRelated> getProductsRelated() {
		return productsRelated;
	}

	public void setProductsRelated(Set<SimpleProductRelated> productsRelated) {
		this.productsRelated = productsRelated;
	}

	public SimpleSupplier getSupplier() {
		return supplier;
	}

	public void setSupplier(SimpleSupplier supplier) {
		this.supplier = supplier;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}


	
}
