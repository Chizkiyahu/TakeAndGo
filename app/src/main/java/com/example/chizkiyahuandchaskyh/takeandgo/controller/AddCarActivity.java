package com.example.chizkiyahuandchaskyh.takeandgo.controller;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
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
import com.example.chizkiyahuandchaskyh.takeandgo.model.entities.Customer;
import com.example.chizkiyahuandchaskyh.takeandgo.model.utils.Constants;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AddCarActivity extends AppCompatActivity {


    protected EditText add_car_line_km, add_car_line_id;
    protected Spinner add_car_line_branch_id, add_car_line_model_id;
    DataSource dataSource = BackendFactory.getDataSource();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);
        inti();
    }

    public void onClickAdd(View view) {

        try {


        }
        catch (Exception e){
            Log.e(Constants.Log.TAG,e.getMessage());
        }

        startActivity(new Intent(this, CarActivity.class));
    }




    void inti(){
        add_car_line_km = findViewById(R.id.add_car_line_km);
        add_car_line_id = findViewById(R.id.add_car_line_id);
        add_car_line_branch_id = findViewById(R.id.add_car_line_branch_id);
        add_car_line_model_id = findViewById(R.id.add_car_line_model_id);

        ArrayAdapter<String> branchIdSpinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,dataSource.getBranchList(). );
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        model_add_gear_box.setAdapter(spinnerArrayAdapter);
    }


    public void onClickAddCustomer(View view) {

    }
}
