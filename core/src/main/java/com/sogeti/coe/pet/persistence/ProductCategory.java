package com.sogeti.coe.pet.persistence;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the PRODUCT_CATEGORIES database table.
 * 
 */
@Entity
@Table(name="PRODUCT_CATEGORIES", schema="PET")
@NamedQueries({ @NamedQuery(name = "ProductCategory.findAll", query = "SELECT p FROM ProductCategory p"),
   @NamedQuery(name = "ProductCategory.findCategory", query = "SELECT p FROM ProductCategory p WHERE p.categoryId = :categoryId ") })
public class ProductCategory implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer categoryId;

	private String description;

	private String name;

	private List<Product> products;

	public ProductCategory() {
	}

	@Id
   @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="prod_cat_gen")
   @SequenceGenerator(name="prod_cat_gen", sequenceName="PRODUCT_CATEGORIES_SEQUENCE", allocationSize=1, schema="PET")
   @Column(name="CATEGORY_ID")
	public Integer getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

  //bi-directional many-to-one association to Product
   @OneToMany(mappedBy="productCategory", cascade = { CascadeType.ALL })
	public List<Product> getProducts() {
		return this.products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Product addProduct(Product product) {
		getProducts().add(product);
		product.setProductCategory(this);

		return product;
	}

	public Product removeProduct(Product product) {
		getProducts().remove(product);
		product.setProductCategory(null);

		return product;
	}

}