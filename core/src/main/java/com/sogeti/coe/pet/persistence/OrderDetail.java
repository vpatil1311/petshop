package com.sogeti.coe.pet.persistence;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the ORDER_DETAILS database table.
 * 
 */
@Entity
@Table(name="ORDER_DETAILS", schema="PET")
@NamedQuery(name="OrderDetail.findAll", query="SELECT o FROM OrderDetail o")
public class OrderDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer odetailsId;

	private Integer cost;

	private Integer quantity;

	private Order order;

	private Product product;

	public OrderDetail() {
	}

   @Id
   @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="order_details_gen")
   @SequenceGenerator(name="order_details_gen", sequenceName="ORDERS_DETAILS_SEQUENCE", allocationSize=1, schema="PET")
   @Column(name="ODETAILS_ID")
	public Integer getOdetailsId() {
		return this.odetailsId;
	}

	public void setOdetailsId(Integer odetailsId) {
		this.odetailsId = odetailsId;
	}

	public Integer getCost() {
		return this.cost;
	}

	public void setCost(Integer cost) {
		this.cost = cost;
	}

	public Integer getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	//bi-directional many-to-one association to Order
   @ManyToOne(fetch=FetchType.LAZY, cascade = { CascadeType.ALL })
   @JoinColumn(name="ORDER_ID", nullable=false)
	public Order getOrder() {
		return this.order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

  //bi-directional many-to-one association to Product
   @ManyToOne(fetch=FetchType.EAGER, cascade = { CascadeType.ALL })
   @JoinColumn(name="PRODUCT_ID", nullable=false)
	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}