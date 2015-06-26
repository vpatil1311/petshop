package com.sogeti.coe.shop.utils;

import java.util.ArrayList;
import java.util.List;

import com.sogeti.coe.shop.beans.Product;


public class ProductCart
{
   private static List<Product> cart = new ArrayList<Product>();

   public static List<Product> getCart()
   {
      return cart;
   }

   public static void setCart(List<Product> cart)
   {
      ProductCart.cart = cart;
   }
   
}
