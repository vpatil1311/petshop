package com.sogeti.coe.shop.beans;

import java.io.Serializable;


public class Product implements Serializable
{
   
   /**
    * <code>serialVersionUID</code> indicates/is used for.
    */
   private static final long serialVersionUID = 1L;

   public Product()
   {
   }
     
   public Product(Integer productId, String description, String name, Integer price, Integer categoryId, String categoryName, Integer qty)
   {
      super();
      this.productId = productId;
      this.description = description;
      this.name = name;
      this.price = price;
      this.categoryId = categoryId;
      this.categoryName = categoryName;
      this.qty = qty;
   }

   private Integer productId;
   private String description;
   private String name;
   private Integer price;
   private Integer categoryId;
   private String categoryName;
   private Integer qty = 1;
   private Integer cost;
   
   public Integer getProductId()
   {
      return productId;
   }
   public void setProductId(Integer productId)
   {
      this.productId = productId;
   }
   public String getDescription()
   {
      return description;
   }
   public void setDescription(String description)
   {
      this.description = description;
   }
   public String getName()
   {
      return name;
   }
   public void setName(String name)
   {
      this.name = name;
   }
   public Integer getPrice()
   {
      return price;
   }
   public void setPrice(Integer price)
   {
      this.price = price;
   }
   public Integer getCategoryId()
   {
      return categoryId;
   }
   public void setCategoryId(Integer categoryId)
   {
      this.categoryId = categoryId;
   }
   public String getCategoryName()
   {
      return categoryName;
   }
   public void setCategoryName(String categoryName)
   {
      this.categoryName = categoryName;
   }
   public Integer getQty()
   {
      return qty;
   }
   public void setQty(Integer qty)
   {
      this.qty = qty;
   }
   
   public Integer getCost()
   {
      return this.price * this.qty;
   }

   public void setCost(Integer cost)
   {
      this.cost = cost;
   }

   @Override
   public int hashCode()
   {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((productId == null) ? 0 : productId.hashCode());
      return result;
   }

   @Override
   public boolean equals(Object obj)
   {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      if (!(obj instanceof Product))
         return false;
      Product other = (Product) obj;
      if (productId == null)
      {
         if (other.productId != null)
            return false;
      }
      else if (!productId.equals(other.productId))
         return false;
      return true;
   }

   @Override
   public String toString()
   {
      return "Product [productId=" + productId + ", description=" + description + ", name=" + name + ", price=" + price + ", categoryId=" + categoryId + ", categoryName=" + categoryName + ", Qty="
            + qty +  ", Cost ="
                  + cost +  "]";
   }
   
}
