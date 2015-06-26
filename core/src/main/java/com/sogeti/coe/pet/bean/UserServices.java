package com.sogeti.coe.pet.bean;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.transaction.annotation.Transactional;

import com.sogeti.coe.pet.persistence.Role;
import com.sogeti.coe.pet.persistence.User;
import com.sogeti.coe.pet.persistence.UserRole;

/**
 * Session Bean implementation class UserBean
 */
public class UserServices {

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public UserServices() {
		// TODO Auto-generated constructor stub
	}

	@Transactional
	public User registerUser(User user) throws Exception {
		TypedQuery<Role> findRoleQuery = entityManager.createNamedQuery(
				"Role.findRole", Role.class);
		findRoleQuery.setParameter("roleId", 1);
		Role role = findRoleQuery.getSingleResult();
		UserRole userRole = new UserRole();
		userRole.setRole(role);
		userRole.setUser(user);
		entityManager.persist(userRole);
		return user;
	}

	public Integer loginUser(String userName, String password) throws Exception {
		TypedQuery<User> findUserQuery = entityManager.createNamedQuery(
				"User.findUser", User.class);
		findUserQuery.setParameter("uname", userName);
		findUserQuery.setParameter("pwd", password);
		User user = findUserQuery.getSingleResult();
		return user.getUserId();
	}

	public User findUserById(Integer userId) throws Exception {
		TypedQuery<User> findUserByIdQuery = entityManager.createNamedQuery(
				"User.findUserById", User.class);
		findUserByIdQuery.setParameter("id", userId);
		User user = findUserByIdQuery.getSingleResult();
		return user;
	}

}
