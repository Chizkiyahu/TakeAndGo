package com.example.chizkiyahuandchaskyh.takeandgo.controller;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.chizkiyahuandchaskyh.takeandgo.R;
import com.example.chizkiyahuandchaskyh.takeandgo.model.beckend.BackendFactory;
import com.example.chizkiyahuandchaskyh.takeandgo.model.beckend.DataSource;
import com.example.chizkiyahuandchaskyh.takeandgo.model.entities.Branch;
import com.example.chizkiyahuandchaskyh.takeandgo.model.entities.Car;
import com.example.chizkiyahuandchaskyh.takeandgo.model.entities.CarModel;
import com.example.chizkiyahuandchaskyh.takeandgo.model.entities.Customer;
import com.example.chizkiyahuandchaskyh.takeandgo.model.utils.Constants;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AddCarActivity extends AppCompatActivity {


    protected EditText add_car_line_km, add_car_line_id;
    protected Spinner add_car_line_branch_id, add_car_line_model_id;
    protected DataSource dataSource = BackendFactory.getDataSource();
    protected ArrayList<String> branchsStringArrayList = new ArrayList<>();
    protected ArrayList<String> carModelStringArrayList = new ArrayList<>();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);
        inti();
    }

    public void onClickAdd(View view) {

        try {
            CarModel carModleId = null;
            int branchID = 0;
            for (CarModel carModel: dataSource.getCarModelList()) {
                if (add_car_line_branch_id.getSelectedItem().toString().equals(carModel.getManufacturerName() + " " + carModel.getModelName())){
                    carModleId = carModel;
                }
            }
            for (Branch branch : dataSource.getBranchList()){
                if (add_car_line_model_id.getSelectedItem().toString().equals(branch.getId() + " " + branch.getAddress().getCity() + " " + branch.getAddress().getStreet())){
                    branchID = branch.getId();
                }
            }
          dataSource.addCar(new Car(Integer.parseInt( add_car_line_id.getText().toString()),
                  branchID,
                  Integer.parseInt( add_car_line_km.getText().toString()),
                  carModleId));

        }
        catch (Exception e){
            Log.e(Constants.Log.TAG,e.getMessage());
        }

        startActivity(new Intent(this, CarActivity.class));
    }




    void inti() {
        add_car_line_km = findViewById(R.id.add_car_line_km);
        add_car_line_id = findViewById(R.id.add_car_line_id);
        add_car_line_branch_id = findViewById(R.id.add_car_line_branch_id);
        add_car_line_model_id = findViewById(R.id.add_car_line_model_id);




        for (Branch branch : dataSource.getBranchList()) {
            branchsStringArrayList.add(branch.getId() + " " + branch.getAddress().getCity() + " " + branch.getAddress().getStreet());
        }

        ArrayAdapter<String> branchIdSpinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, branchsStringArrayList);
        branchIdSpinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view

        add_car_line_branch_id.setAdapter(branchIdSpinnerArrayAdapter);

        for (CarModel carModel : dataSource.getCarModelList()) {
                carModelStringArrayList.add(carModel.getManufacturerName() + " " + carModel.getModelName());
        }
        ArrayAdapter<String> carModelSpinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, carModelStringArrayList);
        carModelSpinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        add_car_line_model_id.setAdapter(carModelSpinnerArrayAdapter);


    }



}