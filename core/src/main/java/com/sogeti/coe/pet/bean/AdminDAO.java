package com.sogeti.coe.pet.bean;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;

import com.sogeti.coe.pet.enums.Roles;
import com.sogeti.coe.pet.persistence.Product;
import com.sogeti.coe.pet.persistence.ProductCategory;
import com.sogeti.coe.pet.persistence.User;
import com.sogeti.coe.pet.persistence.UserRole;

/**
 * Session Bean implementation class AdminBean
 */
public class AdminDAO {

	@PersistenceUnit
	private EntityManagerFactory entityManagerFactory;

	/**
	 * Default constructor.
	 */
	public AdminDAO() {
		// TODO Auto-generated constructor stub
	}

	public Boolean loginAdmin(String userName, String password)
			throws Exception {
	   System.out.println("Check EM " + entityManagerFactory);
	   EntityManager entityManager = this.entityManagerFactory.createEntityManager();
		TypedQuery<User> findAdminQuery = entityManager.createNamedQuery(
				"User.findUser", User.class);
		findAdminQuery.setParameter("uname", userName);
		findAdminQuery.setParameter("pwd", password);
		User user = findAdminQuery.getSingleResult();
		return isAdmin(user.getUserRoles());
	}

	public Boolean addCategory(ProductCategory productCategory)
			throws Exception {
	   EntityManager entityManager = this.entityManagerFactory.createEntityManager();
		entityManager.persist(productCategory);
		return (productCategory.getCategoryId() > 0) ? true : false;
	}

	public List<ProductCategory> getAllCategories() throws Exception {
	   EntityManager entityManager = this.entityManagerFactory.createEntityManager();
		TypedQuery<ProductCategory> categoryQuery = entityManager
				.createNamedQuery("ProductCategory.findAll",
						ProductCategory.class);
		return categoryQuery.getResultList();
	}

	public Boolean addProduct(Product product) throws Exception {
	   EntityManager entityManager = this.entityManagerFactory.createEntityManager();
		TypedQuery<ProductCategory> findCategoryQuery = entityManager
				.createNamedQuery("ProductCategory.findCategory",
						ProductCategory.class);
		findCategoryQuery.setParameter("categoryId", product
				.getProductCategory().getCategoryId());
		ProductCategory productCategory = findCategoryQuery.getSingleResult();
		product.setProductCategory(productCategory);
		
		entityManager.persist(product);
		return (product.getProductId() > 0) ? true : false;
	}

	public List<Product> getAllProducts() throws Exception {
	   System.out.println("Check EM " + entityManagerFactory);
	   EntityManager entityManager = this.entityManagerFactory.createEntityManager();
		TypedQuery<Product> productQuery = entityManager.createNamedQuery(
				"Product.findAll", Product.class);
		return productQuery.getResultList();
	}

	public void updateCategory(ProductCategory category) throws Exception {
	   EntityManager entityManager = this.entityManagerFactory.createEntityManager();
		ProductCategory persistentCategory = entityManager.find(
				ProductCategory.class, category.getCategoryId());
		persistentCategory.setDescription(category.getDescription());
		persistentCategory.setName(category.getName());
		entityManager.persist(persistentCategory);
	}

	private Boolean isAdmin(List<UserRole> roles) {
		Boolean isAdmin = false;
		for (UserRole role : roles) {
			if (role.getRole().getRoleId() == Roles.ADMIN.getId()) {
				isAdmin = true;
				break;
			}
		}
		return isAdmin;
	}
}
