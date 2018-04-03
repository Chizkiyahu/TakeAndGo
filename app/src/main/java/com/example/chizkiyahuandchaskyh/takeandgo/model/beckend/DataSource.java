package com.example.chizkiyahuandchaskyh.takeandgo.model.beckend;

import com.example.chizkiyahuandchaskyh.takeandgo.model.entities.Branch;
import com.example.chizkiyahuandchaskyh.takeandgo.model.entities.Car;
import com.example.chizkiyahuandchaskyh.takeandgo.model.entities.CarModel;
import com.example.chizkiyahuandchaskyh.takeandgo.model.entities.Customer;

import java.util.ArrayList;
import java.util.Collection;

public interface DataSource {

    Customer isExist(Integer id);
    void addCustomer(Customer customer) throws Exception;
    void addCarModle(CarModel  carModel) throws Exception;
    void addCar(Car car) throws Exception;

    Collection<CarModel> getCarModelList();
    ArrayList<Customer> getListCustomer();
    ArrayList<Branch> getBranchList();
    ArrayList<Car> getCarList();


}
