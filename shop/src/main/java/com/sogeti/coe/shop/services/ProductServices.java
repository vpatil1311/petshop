package com.sogeti.coe.shop.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.sogeti.coe.pet.bean.AdminDAO;
import com.sogeti.coe.pet.bean.ProductDAO;
import com.sogeti.coe.shop.beans.Category;
import com.sogeti.coe.shop.utils.ProductUtils;

public class ProductServices
{

   @Autowired
   AdminDAO adminDAO;
   
   @Autowired
   ProductDAO productDAO;
   
   private static ProductUtils productUtils = new ProductUtils();

   public List<com.sogeti.coe.shop.beans.Product> getAllProducts() throws Exception
   {
      return productUtils.productEntitiesToBean(adminDAO.getAllProducts());
   }
   
   public List<Category> getAllCategories() throws Exception
   {
      return productUtils.categoryEntitiesToBean(adminDAO.getAllCategories());
   }
   
   public List<com.sogeti.coe.shop.beans.Product> findProductByCategory(Integer categoryId) throws Exception
   {
      return productUtils.productEntitiesToBean(productDAO.findProductByCategory(categoryId));
   }
}
