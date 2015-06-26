package com.sogeti.coe.shop.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sogeti.coe.shop.beans.Category;
import com.sogeti.coe.shop.beans.Product;

/**
 * Sample controller for going to the home page with a message
 */

@RestController
public class ProductController
{

   private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

   @Autowired
   com.sogeti.coe.shop.services.ProductServices productServices;

   /**
    * Selects the home page and populates the model with a message
    */
   @RequestMapping(value = "/getProducts/{categoryId}", method = RequestMethod.GET)
   public List<Product> getProducts(@PathVariable Integer categoryId)
   {
      logger.info("getProducts() "+categoryId);
      List<Product> allProducts = null;
      try
      {
         if(categoryId == 0)
            allProducts = productServices.getAllProducts();
         else
            allProducts = productServices.findProductByCategory(categoryId);
      }
      catch (Exception e)
      {
         e.printStackTrace(); 
      }
      return allProducts;
   }
   
   
   @RequestMapping(value = "/getCategories", method = RequestMethod.GET)
   public List<Category> getCategories()
   {
      logger.info("getCategories()  ");
      List<Category> allCategories = null;
      try
      {
         allCategories = productServices.getAllCategories();
      }
      catch (Exception e)
      {
         e.printStackTrace(); 
      }
      return allCategories;
   }
   
   @RequestMapping(value = "/addToCart", method = RequestMethod.POST)
   @ResponseStatus(value = HttpStatus.OK)
   public void addToCart(@RequestBody Product product) {
      System.out.println("I was called " + product);
   }
}
