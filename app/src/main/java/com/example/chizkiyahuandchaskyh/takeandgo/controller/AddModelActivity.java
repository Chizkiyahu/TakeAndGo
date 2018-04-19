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
import com.example.chizkiyahuandchaskyh.takeandgo.model.entities.Car;
import com.example.chizkiyahuandchaskyh.takeandgo.model.entities.CarModel;
import com.example.chizkiyahuandchaskyh.takeandgo.model.entities.Customer;
import com.example.chizkiyahuandchaskyh.takeandgo.model.utils.Constants;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddModelActivity extends AppCompatActivity {

    protected EditText model_add_manufacturer_name, model_add_model_name, model_add_engine_capacity, model_add_seating;
    protected Spinner model_add_gear_box;
    DataSource dataSource ;


    void inti(){
        model_add_manufacturer_name = findViewById(R.id.model_add_manufacturer_name);
        model_add_model_name = findViewById(R.id.model_add_model_name);
        model_add_engine_capacity = findViewById(R.id.model_add_engine_capacity);
        model_add_seating = findViewById(R.id.model_add_seating);
        model_add_gear_box = findViewById(R.id.model_add_gear_box);
        dataSource = BackendFactory.getDataSource();
        ArrayList<String> gearBoxEnum = new ArrayList<>();
        for (CarModel.GEAR_BOX gear_box: CarModel.GEAR_BOX.values()) {
            gearBoxEnum.add(gear_box.toString());
        }

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item,gearBoxEnum);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        model_add_gear_box.setAdapter(spinnerArrayAdapter);



    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_model);
        inti();
    }

    public void onClickAddModel(View view) {
        try {
            dataSource.addCarModle(new CarModel(model_add_manufacturer_name.getText().toString(),
                    model_add_model_name.getText().toString(),
                    Integer.parseInt(model_add_engine_capacity.getText().toString()),
                    CarModel.GEAR_BOX.valueOf(model_add_gear_box.getSelectedItem().toString()),
                    Integer.parseInt(model_add_seating.getText().toString())));
        }
        catch (Exception e){
            Log.e(Constants.Log.TAG,e.getMessage());
            int test = 5;
        }

        startActivity(new Intent(AddModelActivity.this, ModelsActivity.class));

    }
}
