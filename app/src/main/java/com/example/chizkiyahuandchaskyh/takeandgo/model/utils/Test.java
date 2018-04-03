package com.example.chizkiyahuandchaskyh.takeandgo.model.utils;

import com.example.chizkiyahuandchaskyh.takeandgo.model.datasource.DatabaseList;
import com.example.chizkiyahuandchaskyh.takeandgo.model.entities.CreditCard;
import com.example.chizkiyahuandchaskyh.takeandgo.model.entities.Customer;

public class Test {

    DatabaseList databaseList = new DatabaseList();
   Customer customer;
  public void  testfunc (){
        try {
            databaseList.addCustomer(new Customer("chezkiaho", "rafol", 206147522, new Long(556636008L), "1@1.com", new CreditCard( new Long(888888888L), 02, 24,222)));
            customer =   databaseList.isExist(206147522);
        }catch (Exception e){
            int stop;
        }


       int stop;
   }
}
