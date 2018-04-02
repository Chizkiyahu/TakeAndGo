package com.example.chizkiyahuandchaskyh.takeandgo.model.beckend;

import com.example.chizkiyahuandchaskyh.takeandgo.model.entities.Branch;
import com.example.chizkiyahuandchaskyh.takeandgo.model.entities.Car;
import com.example.chizkiyahuandchaskyh.takeandgo.model.entities.CarModel;
import com.example.chizkiyahuandchaskyh.takeandgo.model.entities.Customer;

import java.util.ArrayList;

public interface DataSource {
    Customer isExist(Customer customer);
    void addCustomer(Customer customer);
    void addCarModle(CarModel  carModel);
    void addCar(Car car);
    ArrayList<CarModel> getCarModelList();
    ArrayList<Customer> getListCustomer();
    ArrayList<Branch> getBranchList();
    ArrayList<Car> getCarList();

    boolean logon();
    boolean checkUserPass (String username, String Password);
    boolean checkUserFree(String username);
    void addUserPass(String username, String Password) throws Exception;
}
