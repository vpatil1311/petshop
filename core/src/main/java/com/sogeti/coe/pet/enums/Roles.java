package com.sogeti.coe.pet.enums;

public enum Roles
{
   USER(1), ADMIN(2);

   private final short id;

   Roles(int id)
   {
      this.id = (short) id;
   }

   public short getId()
   {
      return this.id;
   }
};