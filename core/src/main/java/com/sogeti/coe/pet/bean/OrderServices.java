package com.sogeti.coe.pet.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.sogeti.coe.pet.enums.OrderStatus;
import com.sogeti.coe.pet.persistence.Order;
import com.sogeti.coe.pet.persistence.OrderDetail;
import com.sogeti.coe.pet.persistence.User;

/**
 * Session Bean implementation class OrderBean
 */
public class OrderServices {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	ProductDAO productBeanLocal;

	@Autowired
	UserServices userBeanLocal;

	/**
	 * Default constructor.
	 */
	public OrderServices() {
	}

	@Transactional
	public Boolean placeOrder(Integer userId,
			Set<com.sogeti.coe.pet.data.Product> cartItems) throws Exception {

		Order order = new Order();
		Integer orderAmount = 0;
		List<OrderDetail> details = new ArrayList<OrderDetail>();

		order.setOrderDate(new Date());
		order.setStatus(com.sogeti.coe.pet.enums.OrderStatus.SUBMITTED
				.getStatus());
		for (com.sogeti.coe.pet.data.Product product : cartItems) {
			OrderDetail detail = new OrderDetail();
			detail.setProduct(productBeanLocal.findProductById(product
					.getProductId()));
			detail.setQuantity(product.getQty());
			detail.setCost(product.getCost());
			orderAmount += product.getCost();
			details.add(detail);
		}
		order.setAmount(orderAmount);
		order.setOrderDetails(details);

		for (OrderDetail detail : order.getOrderDetails()) {
			detail.setOrder(order);
		}

		User user = userBeanLocal.findUserById(userId);
		order.setShipAddress(user.getAddress());
		order.setUser(user);

		entityManager.persist(order);
		return (order.getOrderId() > 0) ? true : false;
	}

	public List<Order> findUserOrders(Integer userId) throws Exception {
		TypedQuery<Order> findUserOrdersQuery = entityManager.createNamedQuery(
				"Order.findUserOrders", Order.class);
		findUserOrdersQuery.setParameter("userId", userId);
		return findUserOrdersQuery.getResultList();
	}

	public List<Order> findAllOrders() throws Exception {
		TypedQuery<Order> findOrdersQuery = entityManager.createNamedQuery(
				"Order.findAll", Order.class);
		return findOrdersQuery.getResultList();
	}

	public void updateOrder(Order order) throws Exception {
		Order persistentOrder = entityManager.find(Order.class,
				order.getOrderId());
		persistentOrder.setStatus(order.getStatus());
		persistentOrder.setShipAddress(order.getShipAddress());
		entityManager.persist(persistentOrder);
	}

	public Boolean cancelOrder(Integer orderId) throws Exception {
		Order persistentOrder = entityManager.find(Order.class, orderId);
		persistentOrder.setStatus(OrderStatus.CANCELLED.getStatus());
		persistentOrder.setCancelDate(new Date());
		entityManager.persist(persistentOrder);
		return true;
	}
}
