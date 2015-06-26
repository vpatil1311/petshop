package com.sogeti.coe.pet.persistence;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ROLES database table.
 * 
 */
@Entity
@Table(name="ROLES", schema="PET")
@NamedQueries({
   @NamedQuery(name="Role.findAll", query="SELECT r FROM Role r"),
   @NamedQuery(name="Role.findRole", query="SELECT r FROM Role r WHERE r.roleId = :roleId")
})
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer roleId;

	private String name;

	private List<UserRole> userRoles;

	public Role() {
	}

   @Id
   @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="role_gen")
   @SequenceGenerator(name="role_gen", sequenceName="ROLES_SEQUENCE", allocationSize=1, schema="PET")
   @Column(name="ROLE_ID")
	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

   //bi-directional many-to-one association to UserRole
   @OneToMany(mappedBy="role", cascade = { CascadeType.ALL })
	public List<UserRole> getUserRoles() {
		return this.userRoles;
	}

	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public UserRole addUserRole(UserRole userRole) {
		getUserRoles().add(userRole);
		userRole.setRole(this);

		return userRole;
	}

	public UserRole removeUserRole(UserRole userRole) {
		getUserRoles().remove(userRole);
		userRole.setRole(null);

		return userRole;
	}

}