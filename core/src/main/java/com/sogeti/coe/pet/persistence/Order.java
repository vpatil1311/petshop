package com.sogeti.coe.pet.persistence;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the ORDERS database table.
 * 
 */
@Entity
@Table(name="ORDERS", schema="PET")
@NamedQueries({
   @NamedQuery(name="Order.findAll", query="SELECT o FROM Order o"),
   @NamedQuery(name="Order.findUserOrders", query="SELECT o FROM Order o WHERE o.user.userId = :userId")
})
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer orderId;

	private Integer amount;

	private Date cancelDate;

	private Date orderDate;

	private String shipAddress;

	private Short status;

	private User user;

	private List<OrderDetail> orderDetails;

	public Order() {
	}

   @Id
   @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="order_gen")
   @SequenceGenerator(name="order_gen", sequenceName="ORDERS_SEQUENCE", allocationSize=1, schema="PET")
   @Column(name="ORDER_ID")
	public Integer getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getAmount() {
		return this.amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

   @Temporal(TemporalType.DATE)
   @Column(name="CANCEL_DATE")
	public Date getCancelDate() {
		return this.cancelDate;
	}

	public void setCancelDate(Date cancelDate) {
		this.cancelDate = cancelDate;
	}

   @Temporal(TemporalType.DATE)
   @Column(name="ORDER_DATE")
	public Date getOrderDate() {
		return this.orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

   @Column(name="SHIP_ADDRESS")
	public String getShipAddress() {
		return this.shipAddress;
	}

	public void setShipAddress(String shipAddress) {
		this.shipAddress = shipAddress;
	}

	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

  //bi-directional many-to-one association to User
   @ManyToOne(fetch=FetchType.EAGER, cascade = { CascadeType.ALL })
   @JoinColumn(name="USER_ID")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

  //bi-directional many-to-one association to OrderDetail
   @OneToMany(mappedBy="order", fetch=FetchType.EAGER, cascade = { CascadeType.ALL })
	public List<OrderDetail> getOrderDetails() {
		return this.orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public OrderDetail addOrderDetail(OrderDetail orderDetail) {
		getOrderDetails().add(orderDetail);
		orderDetail.setOrder(this);

		return orderDetail;
	}

	public OrderDetail removeOrderDetail(OrderDetail orderDetail) {
		getOrderDetails().remove(orderDetail);
		orderDetail.setOrder(null);

		return orderDetail;
	}

}