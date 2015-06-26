package com.sogeti.coe.pet.enums;


public enum OrderStatus
{
   SUBMITTED(1, "Order Placed"), SHIPPED(2, "Shipped"), DELIVERED(3, "Delivered"), CANCELLED(4, "Cancelled");
   
   private final short status;
   private final String readable;

   OrderStatus(int status, String readable)
   {
      this.status = (short) status;
      this.readable = readable;
   }

   public short getStatus()
   {
      return this.status;
   }
   
   public static String getReadableStatus(short status){
      String orderReadableStatus = null;
      
      for(OrderStatus orderStatus: OrderStatus.values()){
         if(orderStatus.getStatus() == status)
            orderReadableStatus = orderStatus.getReadable();
      }
      return orderReadableStatus;
   }
   
   public String getReadable(){
      return this.readable;
   }
   
}
