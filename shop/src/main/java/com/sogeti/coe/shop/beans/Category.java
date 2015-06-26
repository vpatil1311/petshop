package com.sogeti.coe.shop.beans;

import java.io.Serializable;

public class Category implements Serializable
{
   /**
    * <code>serialVersionUID</code> indicates/is used for.
    */
   private static final long serialVersionUID = 1L;

   private Integer categoryId;
   private String name;

   public Integer getCategoryId()
   {
      return categoryId;

   }

   public void setCategoryId(Integer categoryId)
   {
      this.categoryId = categoryId;
   }

   public String getName()
   {
      return name;
   }

   public void setName(String name)
   {
      this.name = name;
   }

   @Override
   public String toString()
   {
      return "Category [categoryId=" + categoryId + ", name=" + name + "]";
   }

}
