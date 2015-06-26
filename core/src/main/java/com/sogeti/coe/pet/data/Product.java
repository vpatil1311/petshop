package com.sogeti.coe.pet.data;

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
     
   public Product(Integer productId, Integer cost, Integer qty)
   {
      super();
      this.productId = productId;
      this.cost = cost;
      this.qty = qty;
   }

   private Integer productId;
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
      return this.cost;
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
      return "Product [productId=" + productId + ", Qty=" + qty + ", Cost =" + cost + "]";
   }
   
}
