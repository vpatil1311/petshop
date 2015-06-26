package com.sogeti.coe.shop.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.naming.NamingException;

import com.sogeti.coe.pet.persistence.Product;
import com.sogeti.coe.pet.persistence.ProductCategory;
import com.sogeti.coe.shop.beans.Category;

public class ProductUtils
{

   public ProductUtils()
   {
   }

   public List<com.sogeti.coe.shop.beans.Product> productEntitiesToBean(List<Product> products)
   {
      List<com.sogeti.coe.shop.beans.Product> beans = new ArrayList<com.sogeti.coe.shop.beans.Product>();
      for (Product product : products)
      {
         beans.add(mapProductEntity(product));
      }
      return beans;
   }

   public com.sogeti.coe.shop.beans.Product mapProductEntity(Product product)
   {
      com.sogeti.coe.shop.beans.Product productBean = new com.sogeti.coe.shop.beans.Product();
      productBean.setDescription(product.getDescription());
      productBean.setName(product.getName());
      productBean.setPrice(product.getPrice());
      productBean.setCategoryId(product.getProductCategory().getCategoryId());
      productBean.setCategoryName(product.getProductCategory().getName());
      productBean.setProductId(product.getProductId());
      return productBean;
   }

   public Set<com.sogeti.coe.pet.data.Product> mapProducts(Set<com.sogeti.coe.shop.beans.Product> cartItems) throws NamingException
   {
      Set<com.sogeti.coe.pet.data.Product> orderDetails = new HashSet<com.sogeti.coe.pet.data.Product>();
      for (com.sogeti.coe.shop.beans.Product product : cartItems)
      {
         com.sogeti.coe.pet.data.Product orderDetail = new com.sogeti.coe.pet.data.Product();
         orderDetail.setProductId(product.getProductId());
         orderDetail.setQty(product.getQty());
         orderDetail.setCost(product.getCost());
         orderDetails.add(orderDetail);
      }
      return orderDetails;
   }
   
   public List<Category> categoryEntitiesToBean(List<ProductCategory> categories)
   {
      List<Category> allCategories = new ArrayList<Category>();
      for (ProductCategory category : categories)
      {
         allCategories.add(mapCategoryEntity(category));
      }
      return allCategories;
   }
   
   public Category mapCategoryEntity(ProductCategory category)
   {
      Category categoryBean = new Category();
      categoryBean.setCategoryId(category.getCategoryId());
      categoryBean.setName(category.getName());
      return categoryBean;
   }
   
}
