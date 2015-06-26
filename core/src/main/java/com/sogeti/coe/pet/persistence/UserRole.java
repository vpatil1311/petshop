package com.sogeti.coe.pet.persistence;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the USER_ROLES database table.
 * 
 */
@Entity
@Table(name="USER_ROLES", schema="PET")
@NamedQuery(name="UserRole.findAll", query="SELECT u FROM UserRole u")
public class UserRole implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer userRoleId;

	private Role role;

	private User user;

	public UserRole() {
	}

   @Id
   @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="user_role_gen")
   @SequenceGenerator(name="user_role_gen", sequenceName="USER_ROLES_SEQUENCE", allocationSize=1, schema="PET")
   @Column(name="USER_ROLE_ID")
	public Integer getUserRoleId() {
		return this.userRoleId;
	}

	public void setUserRoleId(Integer userRoleId) {
		this.userRoleId = userRoleId;
	}

   //bi-directional many-to-one association to Role
   @ManyToOne(fetch=FetchType.LAZY, cascade = { CascadeType.ALL })
   @JoinColumn(name="ROLE_ID")
	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

  //bi-directional many-to-one association to User
   @ManyToOne(fetch=FetchType.LAZY, cascade = { CascadeType.ALL })
   @JoinColumn(name="USER_ID")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}