package com.example.chizkiyahuandchaskyh.takeandgo.model.datasource;


import com.example.chizkiyahuandchaskyh.takeandgo.model.beckend.DataSource;
import com.example.chizkiyahuandchaskyh.takeandgo.model.entities.Address;
import com.example.chizkiyahuandchaskyh.takeandgo.model.entities.Branch;
import com.example.chizkiyahuandchaskyh.takeandgo.model.entities.Car;
import com.example.chizkiyahuandchaskyh.takeandgo.model.entities.CarModel;
import com.example.chizkiyahuandchaskyh.takeandgo.model.entities.CreditCard;
import com.example.chizkiyahuandchaskyh.takeandgo.model.entities.Customer;
import com.example.chizkiyahuandchaskyh.takeandgo.model.entities.Order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chezkiaho on 19.3.2018.
 */


public class DatabaseList implements  DataSource  {


    private String username;
    private String password;
    private boolean logon;

    private Map<Integer, Customer> customerMap = new HashMap<>();
    private Map<Integer , CarModel> carModelMap = new HashMap<>();
    private Map<Integer , Car> carMap = new HashMap<>();
    private Map<Integer, Branch> branchMap = new HashMap<>();
    private Map<String, String> usersPassMap = new HashMap<>();

    public DatabaseList() {

    }

    @Override
    public Customer isExist(Integer id) {

      return customerMap.get(id);
    }

    @Override
    public void addCustomer(Customer customer) throws Exception {

        if(isExist(customer.getId()) != null){
            throw new  Exception("the customer already exists");
        }
        customerMap.put(customer.getId(), customer);
    }

    @Override
    public Address getAddressByID(int id) {
        return null;
    }

    @Override
    public Branch getBranchByID(int id) {
        return null;
    }

    @Override
    public CarModel getCarModelById(int id) {
        return null;
    }

    @Override
    public Car getCarByID(int id) {
        return null;
    }

    @Override
    public CreditCard getCreditCardByID(int id) {
        return null;
    }

    @Override
    public Customer getCustomerById(int id) {
        return null;
    }


    @Override
    public Order getOrderById(int id) {
        return null;
    }

    @Override
    public void addCarModle(CarModel carModel) throws Exception {
        if(carModelMap.get(carModel.getCodeModel()) != null){
            throw new Exception("the carModle already exists");
        }
        carModelMap.put(carModel.getCodeModel(), carModel);
    }

    @Override
    public void addCar(Car car) throws Exception{
        if(carMap.get(car.getId()) != null){
            throw new Exception("the car already exists");
        }
        carMap.put(car.getId(), car);
    }

    @Override
    public void addBranch(Branch branch) {

        branchMap.put(branch.getId(), branch);

    }

    @Override
    public ArrayList<CarModel> getCarModelList() {
        return new ArrayList<>(carModelMap.values());
    }

    @Override
    public ArrayList<Customer> getCustomerList() {
        return new ArrayList<>(customerMap.values());
    }

    @Override
    public ArrayList<Branch> getBranchList() {
        return new ArrayList<>(branchMap.values());
    }

    @Override
    public ArrayList<Car> getCarList() {
        return new ArrayList<>(carMap.values());
    }


    @Override
    public ArrayList<Address> getAddressesList() {
        return null;
    }

    @Override
    public ArrayList<CreditCard> getCreditCardsList() {
        return null;
    }

    @Override
    public ArrayList<Order> getOrdersList() {
        return null;
    }

    @Override
    public boolean tryUserPass(String username, String password) throws Exception {
        if (username.equals("") || username.equals(null) ){
            throw new Exception("the username cant be empty");
        }
        if ( password.equals("") || password.equals(null)){
            throw new Exception("the password cant be empty");
        }
        if(usersPassMap.containsKey(username)){
            return usersPassMap.get(username).equals(password);
        }

    return false;
    }

    @Override
    public boolean checkUserIsFree(String username) throws Exception {
        if (username.equals("") || username.equals(null) ){
            throw new Exception("the username cant be empty");
        }
        return !usersPassMap.containsKey(username);
    }

    @Override
    public void addUserPass(String username, String password) throws Exception{
        checkUserIsFree(username);
        if ( password.equals("") || password.equals(null)){
            throw new Exception("the password cant be empty");
        }
        usersPassMap.put(username, password);
    }

    @Override
    public void addCreditCard(CreditCard creditCard) {

    }

    @Override
    public ArrayList<Car> getFreeCarList() {
        return null;
    }
}
