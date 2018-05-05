package com.example.chizkiyahuandchaskyh.takeandgo.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.chizkiyahuandchaskyh.takeandgo.R;
import com.example.chizkiyahuandchaskyh.takeandgo.model.beckend.BackendFactory;
import com.example.chizkiyahuandchaskyh.takeandgo.model.beckend.DataSource;
import com.example.chizkiyahuandchaskyh.takeandgo.model.entities.Branch;
import com.example.chizkiyahuandchaskyh.takeandgo.model.entities.Car;
import com.example.chizkiyahuandchaskyh.takeandgo.model.entities.CarModel;
import com.example.chizkiyahuandchaskyh.takeandgo.model.utils.Constants;

import java.util.ArrayList;

public class CarActivity extends AppCompatActivity {


    protected EditText kmView, idView;
    protected Spinner branchIDView, modelIDView;
    protected DataSource dataSource = BackendFactory.getDataSource();
    protected ArrayList<String> branchesStringArrayList = new ArrayList<>();
    protected ArrayList<String> carModelStringArrayList = new ArrayList<>();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);
        inti();
    }

    public void onClickAdd(View view) {

        try {
            CarModel carModelId = null;
            int branchID = 0;

            for (CarModel carModel: dataSource.getCarModelList()) {
                if (modelIDView.getSelectedItem().toString().equals(carModel.getManufacturerName() + " " + carModel.getModelName())){
                    carModelId = carModel;
                }
            }
            for (Branch branch : dataSource.getBranchList()){
                if (branchIDView.getSelectedItem().toString().equals(branch.getId() + " " + branch.getAddress().getCity() + " " + branch.getAddress().getStreet())){
                    branchID = branch.getId();
                }
            }
          dataSource.addCar(new Car(Integer.parseInt( idView.getText().toString()),
                  branchID,
                  Integer.parseInt( kmView.getText().toString()),
                  carModelId));

        }
        catch (Exception e){
            Log.e(Constants.Log.TAG,e.getMessage());
        }

        //startActivity(new Intent(this, CarsActivity.class));
        finish();
    }




    void inti() {
        kmView = findViewById(R.id.add_car_line_km);
        idView = findViewById(R.id.add_car_line_id);
        branchIDView = findViewById(R.id.add_car_line_branch_id);
        modelIDView = findViewById(R.id.add_car_line_model_id);




        for (Branch branch : dataSource.getBranchList()) {
            branchesStringArrayList.add(branch.getId() + " " + branch.getAddress().getCity() + " " + branch.getAddress().getStreet());
        }

        ArrayAdapter<String> branchIdSpinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, branchesStringArrayList);
        branchIdSpinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view

        branchIDView.setAdapter(branchIdSpinnerArrayAdapter);

        for (CarModel carModel : dataSource.getCarModelList()) {
                carModelStringArrayList.add(carModel.getManufacturerName() + " " + carModel.getModelName());
        }
        ArrayAdapter<String> carModelSpinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, carModelStringArrayList);
        carModelSpinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        modelIDView.setAdapter(carModelSpinnerArrayAdapter);


    }



}