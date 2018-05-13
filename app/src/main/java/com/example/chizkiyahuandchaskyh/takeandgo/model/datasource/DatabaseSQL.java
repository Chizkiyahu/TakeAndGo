package com.example.chizkiyahuandchaskyh.takeandgo.model.datasource;

import android.content.ContentValues;
import android.renderscript.Element;
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
        getAddressesList();
        if (id == 0){
            return null;
        }
        return addresses.get(id);
    }

    @Override
    public Branch getBranchByID(int id) {
        getBranchList();
        if (id == 0){
            return null;
        }
        return branches.get(id);
    }

    @Override
    public Car getCarByID(int id) {
        getCarList();
        if (id == 0){
            return null;
        }
        return cars.get(id);
    }

    @Override
    public CreditCard getCreditCardByID(int id) {
        getCreditCardsList();
        if (id == 0){
            return null;
        }
        return creditCards.get(id);
    }

    @Override
    public Customer getCustomerById(int id) {
        getCustomerList();
        if (id == 0){
            return null;
        }
        return customers.get(id);
    }

    @Override
    public Order getOrderById(int id) {
        getOrdersList();
        if (id == 0){
            return null;
        }
        return orders.get(id);
    }

    @Override
    public Customer isExist(Integer id) {
        return getCustomerById(id);
    }

    @Override
    public CarModel getCarModelById(int id) {
        getCarModelList();
        if (id == 0){
            return null;
        }
        return carModels.get(id);
    }

    @Override
    public void addCustomer(Customer customer) throws Exception {
        try {
            String url = WEB_URL + "addCustomer.php" ;

            final ContentValues values = new ContentValues();
            values.put("id", customer.getId());
            values.put("lastName", customer.getLastName());
            values.put("firstName",customer.getFirstName());
            values.put("phoneNumber", customer.getPhoneNumber());
            values.put("email", customer.getEmail());
            if (customer.getCreditCard() != null){
                values.put("creditCardID", customer.getCreditCard().getId());
            }
            Php.POST( url, values );
        } catch (Exception e) {
            Log.e(Constants.Log.TAG,e.getMessage());
        }

    }

    @Override
    public void addCarModle(CarModel carModel) throws Exception {
        try {
            String url = WEB_URL + "addCarModel.php" ;

            final ContentValues values = new ContentValues();
            values.put("manufacturerName", carModel.getManufacturerName());
            values.put("modelName", carModel.getModelName());
            values.put("engineCapacity", carModel.getEngineCapacity());
            values.put("seating", carModel.getSeating());
            values.put("gearBox", carModel.getGearBox().toString());


            Php.POST( url, values );
        } catch (Exception e) {
            Log.e(Constants.Log.TAG,e.getMessage());
        }
    }

    @Override
    public void addCar(Car car) throws Exception {
        try {
            String url = WEB_URL + "addCar.php" ;

            final ContentValues values = new ContentValues();
            values.put("id", car.getId());
            values.put("branchID", car.getBranchID());
            values.put("km", car.getKm());
            values.put("modelID", car.getModelID());

            Php.POST( url, values );
        } catch (Exception e) {
            Log.e(Constants.Log.TAG,e.getMessage());
        }

    }

    @Override
    public void addBranch(Branch branch) throws Exception {
        try {
            String url = WEB_URL + "addBranch.php" ;

            final ContentValues values = new ContentValues();
            values.put("id", branch.getId());
            values.put("numParkingSpaces", branch.getNumParkingSpaces());
            //values.put("addressID",);

            Php.POST( url, values );
        } catch (Exception e) {
            Log.e(Constants.Log.TAG,e.getMessage());
        }

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
    public ArrayList<Customer> getCustomerList() {
        try {
            if (customers == null){
                customers = new HashMap<>();
            }
            customers.clear();

            String json = Php.GET( WEB_URL + "getCustomerList.php" );
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
        try {
            if (branches == null){
                branches = new HashMap<>();
            }
            branches.clear();

            String json = Php.GET( WEB_URL + "getBranchList.php" );
            JSONArray array = new JSONObject( json ).getJSONArray( "Branch" );
            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = array.getJSONObject( i );
                branches.put(jsonObject.getInt( "id" ),
                        new Branch(jsonObject.getInt( "id" ),
                                jsonObject.getInt( "numParkingSpaces" ),
                                new Address(getAddressByID( jsonObject.getInt( "addressID")))));
            }
        } catch (Exception e) {
            Log.e(Constants.Log.TAG,e.getMessage());
        }
        return new ArrayList<>(branches.values());

    }

    @Override
    public ArrayList<Address> getAddressesList() {
        try {
            if (addresses == null){
                addresses = new HashMap<>();
            }
            addresses.clear();

            String json = Php.GET( WEB_URL + "getAddressesList.php" );
            JSONArray array = new JSONObject( json ).getJSONArray( "Address" );
            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = array.getJSONObject( i );
                addresses.put(jsonObject.getInt( "id" ),
                        new Address(jsonObject.getInt( "id" ),
                                jsonObject.getString( "country" ),
                                jsonObject.getString("city"),
                                jsonObject.getString("street"),
                                jsonObject.getInt("houseNum"),
                                jsonObject.getDouble("Latitude"),
                                jsonObject.getDouble("longitude")));
            }
        } catch (Exception e) {
            Log.e(Constants.Log.TAG,e.getMessage());
        }
        return new ArrayList<>(addresses.values());
    }

    @Override
    public ArrayList<CreditCard> getCreditCardsList() {
       /* try {
            if (creditCards == null){
                creditCards = new HashMap<>();
            }
            creditCards.clear();

            String json = Php.GET( WEB_URL + "getCreditCardsList.php" );
            JSONArray array = new JSONObject( json ).getJSONArray( "Branch" );
            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = array.getJSONObject( i );
                creditCards.put(jsonObject.getInt( "id" ),
                        new Address(jsonObject.getInt( "id" ),
                                jsonObject.getString( "country" ),
                                jsonObject.getString("city"),
                                jsonObject.getString("street"),
                                jsonObject.getInt("houseNum"),
                                jsonObject.getDouble("Latitude"),
                                jsonObject.getDouble("longitude")));
            }
        } catch (Exception e) {
            Log.e(Constants.Log.TAG,e.getMessage());
        }
        return new ArrayList<>(creditCards.values());*/
       return null;
    }

    @Override
    public ArrayList<Order> getOrdersList() {
        try {
            if (orders == null){
                orders = new HashMap<>();
            }
            orders.clear();

            String json = Php.GET( WEB_URL + "getOrdersList.php" );
            JSONArray array = new JSONObject( json ).getJSONArray( "Order" );
            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = array.getJSONObject( i );
                orders.put(jsonObject.getInt( "orderID" ),
                        new Order(jsonObject.getInt( "orderID" ),
                                jsonObject.getInt( "customerID" ),
                                Order.STATUS.valueOf(jsonObject.getString("status")),
                                Element.DataType.valueOf("start"),
                                Element.DataType.valueOf("end"),
                                jsonObject.getInt("startKM"),
                                jsonObject.getInt("endKM"),
                                Boolean.getBoolean(jsonObject.getString("returnNonFilledTank")),
                                jsonObject.getInt("quantityOfLitersPerBill"),
                                jsonObject.getInt("amountToPay")));
            }
        } catch (Exception e) {
            Log.e(Constants.Log.TAG,e.getMessage());
        }
        return new ArrayList<>(orders.values());
    }

    @Override
    public ArrayList<Car> getCarList() {
        try {
            if (cars == null){
                cars = new HashMap<>();
            }
            cars.clear();

            String json = Php.GET( WEB_URL + "getCarList.php" );
            JSONArray array = new JSONObject( json ).getJSONArray( "Car" );
            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = array.getJSONObject( i );
                cars.put(jsonObject.getInt( "id" ),
                        new Car(jsonObject.getInt( "id" ),
                                jsonObject.getInt( "branchID" ),
                                jsonObject.getInt("km"),
                                new CarModel(getCarModelById(jsonObject.getInt("modelID")))));
            }
        } catch (Exception e) {
            Log.e(Constants.Log.TAG,e.getMessage());
        }
        return new ArrayList<>(cars.values());
    }


    @Override
    public boolean TryUserPass(String username, String Password) throws Exception {
        return true;//test
    }

    @Override
    public boolean checkUserIsFree(String username) throws Exception {
        return false;
    }

    @Override
    public void addUserPass(String username, String Password) throws Exception {

    }

}
