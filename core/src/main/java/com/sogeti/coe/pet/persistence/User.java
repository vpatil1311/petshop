package com.sogeti.coe.pet.persistence;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the USERS database table.
 * 
 */
@Entity
@Table(name="USERS", schema="PET")
@NamedQueries({ @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
      @NamedQuery(name = "User.findUser", query = "SELECT u FROM User u WHERE u.username = :uname AND u.password = :pwd"),
      @NamedQuery(name = "User.findUserById", query = "SELECT u FROM User u WHERE u.userId = :id") })
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer userId;

	private String address;

	private String city;

	private String email;

	private String fullName;

	private String password;

	private String pinCode;

	private Short status;

	private String username;

	private List<Order> orders;

	private List<UserRole> userRoles;

	public User() {
	}
	
   @Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="user_gen")
   @SequenceGenerator(name="user_gen", sequenceName="USERS_SEQUENCE", allocationSize=1, schema="PET")
	@Column(name="USER_ID")
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name="FULL_NAME")
	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name="PIN_CODE")
	public String getPinCode() {
		return this.pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

   //bi-directional many-to-one association to Order
   @OneToMany(mappedBy="user", cascade = { CascadeType.ALL })
	public List<Order> getOrders() {
		return this.orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public Order addOrder(Order order) {
		getOrders().add(order);
		order.setUser(this);

		return order;
	}

	public Order removeOrder(Order order) {
		getOrders().remove(order);
		order.setUser(null);

		return order;
	}

	
  //bi-directional many-to-one association to UserRole
	@OneToMany(mappedBy="user", cascade = { CascadeType.ALL })
	public List<UserRole> getUserRoles() {
		return this.userRoles;
	}

	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public UserRole addUserRole(UserRole userRole) {
		getUserRoles().add(userRole);
		userRole.setUser(this);

		return userRole;
	}

	public UserRole removeUserRole(UserRole userRole) {
		getUserRoles().remove(userRole);
		userRole.setUser(null);

		return userRole;
	}

}