package com.example.chizkiyahuandchaskyh.takeandgo.model.beckend;

import com.example.chizkiyahuandchaskyh.takeandgo.model.entities.Address;
import com.example.chizkiyahuandchaskyh.takeandgo.model.entities.Branch;
import com.example.chizkiyahuandchaskyh.takeandgo.model.entities.Car;
import com.example.chizkiyahuandchaskyh.takeandgo.model.entities.CarModel;
import com.example.chizkiyahuandchaskyh.takeandgo.model.entities.CreditCard;
import com.example.chizkiyahuandchaskyh.takeandgo.model.entities.Customer;
import com.example.chizkiyahuandchaskyh.takeandgo.model.entities.Order;

import java.util.ArrayList;

public interface DataSource {

    Customer isExist(Integer id);
    void addCustomer(Customer customer) throws Exception;
    void addCarModle(CarModel  carModel) throws Exception;
    void addCar(Car car) throws Exception;
    void addBranch(Branch branch) throws Exception;
    void addCreditCard(CreditCard creditCard) throws Exception;



    ArrayList<CarModel> getCarModelList();
    ArrayList<Customer> getCustomerList();
    ArrayList<Branch> getBranchList();
    ArrayList<Car> getCarList();
    ArrayList<Address> getAddressesList();
    ArrayList<CreditCard> getCreditCardsList();
    ArrayList<Order> getOrdersList();
    ArrayList<Car> getFreeCarList();

    Address getAddressByID(int id);
    Branch getBranchByID(int id);
    Car getCarByID(int id);
    CreditCard getCreditCardByID(int id);
    Customer getCustomerById(int id);
    Order getOrderById(int id);
    CarModel getCarModelById(int id);


    boolean tryUserPass(String username, String Password) throws Exception;
    boolean checkUserIsFree(String username) throws Exception;
    void addUserPass(String username, String Password) throws Exception;
}
