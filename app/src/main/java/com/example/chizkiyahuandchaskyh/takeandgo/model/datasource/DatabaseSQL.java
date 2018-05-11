package com.example.chizkiyahuandchaskyh.takeandgo.model.datasource;

import android.util.Log;

import com.example.chizkiyahuandchaskyh.takeandgo.model.beckend.DataSource;
import com.example.chizkiyahuandchaskyh.takeandgo.model.entities.Address;
import com.example.chizkiyahuandchaskyh.takeandgo.model.entities.Branch;
import com.example.chizkiyahuandchaskyh.takeandgo.model.entities.Car;
import com.example.chizkiyahuandchaskyh.takeandgo.model.entities.CarModel;
import com.example.chizkiyahuandchaskyh.takeandgo.model.entities.CreditCard;
import com.example.chizkiyahuandchaskyh.takeandgo.model.entities.Customer;
import com.example.chizkiyahuandchaskyh.takeandgo.model.entities.Order;
import com.example.chizkiyahuandchaskyh.takeandgo.model.utils.Constants;
import com.example.chizkiyahuandchaskyh.takeandgo.model.utils.Php;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DatabaseSQL implements DataSource {

    private static final String WEB_URL = "http://rafol.vlab.jct.ac.il/TakeAndGo/";



    private Map<String, String> usersPassMap = new HashMap<>();

    private Map<Integer, Address> addresses ;
    private Map<Integer, Branch> branches ;
    private Map<Integer, Car> cars ;
    private Map<Integer, CarModel> carModels ;
    private Map<Integer, CreditCard> creditCards ;
    private Map<Integer, Customer> customers ;
    private Map<Integer, Order> orders ;


    @Override
    public Address getAddressByID(int id) {
        if (id == 0){
            return null;
        }
        return addresses.get(id);
    }

    @Override
    public Branch getBranchByID(int id) {
        if (id == 0){
            return null;
        }
        return branches.get(id);
    }

    @Override
    public Car getCarByID(int id) {
        if (id == 0){
            return null;
        }
        return cars.get(id);
    }

    @Override
    public CreditCard getCreditCardByID(int id) {
        if (id == 0){
            return null;
        }
        return creditCards.get(id);
    }

    @Override
    public Customer getCustomerById(int id) {
        if (id == 0){
            return null;
        }
        return customers.get(id);
    }

    @Override
    public Order getOrderById(int id) {
        if (id == 0){
            return null;
        }
        return orders.get(id);
    }

    @Override
    public Customer isExist(Integer id) {
        return null;
    }

    @Override
    public void addCustomer(Customer customer) throws Exception {

    }

    @Override
    public void addCarModle(CarModel carModel) throws Exception {

    }

    @Override
    public void addCar(Car car) throws Exception {

    }

    @Override
    public void addBranch(Branch branch) throws Exception {

    }

    @Override
    public ArrayList<CarModel> getCarModelList() {
        try {
            if (carModels == null){
                carModels = new HashMap<>();
            }
            carModels.clear();

            String json = Php.GET( WEB_URL + "getCarModelList.php" );
            JSONArray array = new JSONObject( json ).getJSONArray( "CarModel" );
            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = array.getJSONObject( i );
                carModels.put(jsonObject.getInt( "codeModel" ),
                        new CarModel(
                                jsonObject.getInt( "codeModel" ),
                                jsonObject.getString( "manufacturerName" ),
                                jsonObject.getString( "modelName" ),
                                jsonObject.getInt( "engineCapacity" ),
                                CarModel.GEAR_BOX.valueOf( jsonObject.getString( "gearBox" )),
                                jsonObject.getInt( "seating" )
                        ));
            }
        } catch (Exception e) {
            Log.e(Constants.Log.TAG,e.getMessage());
        }
        return new ArrayList<>(carModels.values());
    }




    @Override
    public ArrayList<Customer> getListCustomer() {
        try {
            if (customers == null){
                customers = new HashMap<>();
            }
            customers.clear();

            String json = Php.GET( WEB_URL + "getListCustomer.php" );
            JSONArray array = new JSONObject( json ).getJSONArray( "Customer" );
            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = array.getJSONObject( i );
                customers.put(jsonObject.getInt( "id" ),
                        new Customer(

                                jsonObject.getString( "lastName" ),
                                jsonObject.getString( "firstName" ),
                                jsonObject.getInt( "id" ),
                                jsonObject.getLong( "phoneNumber" ),
                                jsonObject.getString( "email"),
                                getCreditCardByID(jsonObject.getInt( "creditCardID" ))
                                )
                        );
            }
        } catch (Exception e) {
            Log.e(Constants.Log.TAG,e.getMessage());
        }
        return new ArrayList<>(customers.values());
    }

    @Override
    public ArrayList<Branch> getBranchList() {
        return null;
    }


    @Override
    public ArrayList<Car> getCarList() {
        return null;
    }


    @Override
    public boolean TryUserPass(String username, String Password) throws Exception {
        return false;
    }

    @Override
    public boolean checkUserIsFree(String username) throws Exception {
        return false;
    }

    @Override
    public void addUserPass(String username, String Password) throws Exception {

    }
}
