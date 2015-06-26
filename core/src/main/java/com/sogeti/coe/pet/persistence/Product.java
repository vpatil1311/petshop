package com.sogeti.coe.pet.persistence;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the PRODUCTS database table.
 * 
 */
@Entity
@Table(name="PRODUCTS", schema="PET")
@NamedQueries({
   @NamedQuery(name="Product.findAll", query="SELECT p FROM Product p"),
   @NamedQuery(name="Product.findNameMatch", query="SELECT p FROM Product p WHERE p.name LIKE :pName"),
   @NamedQuery(name="Product.findDescMatch", query="SELECT p FROM Product p WHERE p.description LIKE :pDesc"),
   @NamedQuery(name="Product.findOne", query="SELECT p FROM Product p WHERE p.productId = :id "),
   @NamedQuery(name="Product.findByCategory", query="SELECT p FROM Product p WHERE p.productCategory.categoryId = :id ")
})
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer productId;

	private String description;

	private String name;

	private Integer price;

	private List<OrderDetail> orderDetails;

	private ProductCategory productCategory;

	public Product() {
	}
	
	@Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prod_gen")
   @SequenceGenerator(name = "prod_gen", sequenceName = "PRODUCTS_SEQUENCE", allocationSize = 1, schema="PET")
   @Column(name = "PRODUCT_ID")
	public Integer getProductId() {
		return this.productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
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

	public Integer getPrice() {
		return this.price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	//bi-directional many-to-one association to OrderDetail
   @OneToMany(mappedBy="product", cascade = { CascadeType.ALL })
	public List<OrderDetail> getOrderDetails() {
		return this.orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public OrderDetail addOrderDetail(OrderDetail orderDetail) {
		getOrderDetails().add(orderDetail);
		orderDetail.setProduct(this);

		return orderDetail;
	}

	public OrderDetail removeOrderDetail(OrderDetail orderDetail) {
		getOrderDetails().remove(orderDetail);
		orderDetail.setProduct(null);

		return orderDetail;
	}

	//bi-directional many-to-one association to ProductCategory
   @ManyToOne(fetch=FetchType.EAGER, cascade = { CascadeType.ALL })
   @JoinColumn(name="CATEGORY_ID")
	public ProductCategory getProductCategory() {
		return this.productCategory;
	}

	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}

}