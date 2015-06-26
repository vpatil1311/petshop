package com.sogeti.coe.pet.bean;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.sogeti.coe.pet.persistence.Product;
import com.sogeti.coe.pet.persistence.ProductCategory;

/**
 * Session Bean implementation class ProductBean
 */
public class ProductDAO {

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public ProductDAO() {
		// TODO Auto-generated constructor stub
	}

	public List<Product> findProducts(String text) throws Exception {
		List<Product> products = null;

		TypedQuery<Product> nameMatchQuery = entityManager.createNamedQuery(
				"Product.findNameMatch", Product.class);
		nameMatchQuery.setParameter("pName", "%" + text + "%");
		products = nameMatchQuery.getResultList();
		if (products == null) {
			TypedQuery<Product> descMatchQuery = entityManager
					.createNamedQuery("Product.findDescMatch", Product.class);
			descMatchQuery.setParameter("pDesc", "%" + text + "%");
			products = descMatchQuery.getResultList();
		}

		return products;
	}

	public Product findProductById(Integer id) throws Exception {
		TypedQuery<Product> idMatchQuery = entityManager.createNamedQuery(
				"Product.findOne", Product.class);
		idMatchQuery.setParameter("id", id);
		Product product = idMatchQuery.getSingleResult();
		return product;

	}

	public List<Product> findProductByCategory(Integer categoryId)
			throws Exception {
		TypedQuery<Product> findByCategory = null;
		if (categoryId == 0) {
			findByCategory = entityManager.createNamedQuery("Product.findAll",
					Product.class);
		} else {
			findByCategory = entityManager.createNamedQuery(
					"Product.findByCategory", Product.class);
			findByCategory.setParameter("id", categoryId);
		}
		return findByCategory.getResultList();
	}

	public void updateProduct(Product product) throws Exception {
		Product persistentProduct = entityManager.find(Product.class,
				product.getProductId());
		persistentProduct.setDescription(product.getDescription());
		persistentProduct.setName(product.getName());
		TypedQuery<ProductCategory> findCategoryQuery = entityManager
				.createNamedQuery("ProductCategory.findCategory",
						ProductCategory.class);
		findCategoryQuery.setParameter("categoryId", product
				.getProductCategory().getCategoryId());
		ProductCategory productCategory = findCategoryQuery.getSingleResult();
		persistentProduct.setProductCategory(productCategory);
		entityManager.persist(persistentProduct);
	}
}
