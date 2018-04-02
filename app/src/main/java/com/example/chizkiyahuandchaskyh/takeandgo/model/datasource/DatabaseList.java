package com.example.chizkiyahuandchaskyh.takeandgo.model.datasource;

import java.util.ArrayList;

import com.example.chizkiyahuandchaskyh.takeandgo.model.beckend.DataSource;
import com.example.chizkiyahuandchaskyh.takeandgo.model.entities.Branch;
import com.example.chizkiyahuandchaskyh.takeandgo.model.entities.Car;
import com.example.chizkiyahuandchaskyh.takeandgo.model.entities.CarModel;
import com.example.chizkiyahuandchaskyh.takeandgo.model.entities.Customer;

/**
 * Created by chezkiaho on 19.3.2018.
 */


public class DatabaseList implements  DataSource {


    private String username;
    private String password;
    private boolean logon;

    public DatabaseList() {
        logon = false;
    }

    @Override
    public Customer isExist(Customer customer) {
        return null;
    }

    @Override
    public void addCustomer(Customer customer) {

    }

    @Override
    public void addCarModle(CarModel carModel) {

    }

    @Override
    public void addCar(Car car) {

    }

    @Override
    public ArrayList<CarModel> getCarModelList() {
        return null;
    }

    @Override
    public ArrayList<Customer> getListCustomer() {
        return null;
    }

    @Override
    public ArrayList<Branch> getBranchList() {
        return null;
    }

    @Override
    public ArrayList<Car> getCarList() {
        return null;
    }


    private class Users {

        String username;
        String password;

        public Users(String user, String pass){
            this.username = user;
            this.password = pass;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public String getUsername() {
            return username;
        }
    }

    ArrayList<Users> users = new ArrayList<>();

    @Override
    public boolean logon() {
        return this.logon;
    }

    @Override
    public boolean checkUserPass(String username, String password) {

        for (Users userPass:users) {
            if (userPass.getUsername().equals(username)){
                if (userPass.getPassword().equals(password)) {
                    return true;
                }
                else {
                    return  false;
                }
            }
        }
    return false;
    }

    @Override
    public boolean checkUserFree(String username) {
        for (Users userPass:users) {
            if (userPass.getUsername().equals(username)){
                return false;
            }
        }
        return true;
    }

    @Override
    public void addUserPass(String username, String password) throws Exception{
        if(checkUserFree(username)){
            users.add(new Users(username, password));
        }else {
            throw new Exception("the username alredy exsiste");
        }

    }
}
