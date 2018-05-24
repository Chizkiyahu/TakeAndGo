package com.example.chizkiyahuandchaskyh.takeandgo.controller;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.chizkiyahuandchaskyh.takeandgo.R;
import com.example.chizkiyahuandchaskyh.takeandgo.model.beckend.BackendFactory;
import com.example.chizkiyahuandchaskyh.takeandgo.model.beckend.DataSource;
import com.example.chizkiyahuandchaskyh.takeandgo.model.entities.Car;
import com.example.chizkiyahuandchaskyh.takeandgo.model.entities.CarModel;
import com.example.chizkiyahuandchaskyh.takeandgo.model.utils.Constants;

import java.util.ArrayList;

public class ModelActivity extends AppCompatActivity {

    protected EditText manufacturerNameView, modelNameView, engineCapacityView, seatingView;
    protected boolean manufacturerNameIsCorrectly = false, modelNameIsCorrectly = false,
            engineCapacityIsCorrectly = false, seatingIsCorrectly = false;
    protected Spinner gearBoxSpinnerView;
    protected Button addButton;
    DataSource dataSource;


    void inti() {
        manufacturerNameView = findViewById(R.id.model_add_manufacturer_name);
        modelNameView = findViewById(R.id.model_add_model_name);
        engineCapacityView = findViewById(R.id.model_add_engine_capacity);
        seatingView = findViewById(R.id.model_add_seating);
        gearBoxSpinnerView = findViewById(R.id.model_add_gear_box);
        dataSource = BackendFactory.getDataSource();
        ArrayList<String> gearBoxEnum = new ArrayList<>();
        for (CarModel.GEAR_BOX gear_box : CarModel.GEAR_BOX.values()) {
            gearBoxEnum.add(gear_box.toString());
        }

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, gearBoxEnum);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        gearBoxSpinnerView.setAdapter(spinnerArrayAdapter);

        addButton =  findViewById(R.id.model_add_buttun);
        addButton.setEnabled(false);
        manufacturerNameView.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(s.toString().trim().length()==0){
                    addButton.setEnabled(false);
                    manufacturerNameIsCorrectly = false;
                } else {
                    manufacturerNameIsCorrectly = true;
                    if (manufacturerNameIsCorrectly && modelNameIsCorrectly && engineCapacityIsCorrectly
                            && seatingIsCorrectly ) {
                        addButton.setEnabled(true);
                    }

                }
            }


        });
        modelNameView.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(s.toString().trim().length()==0){
                    addButton.setEnabled(false);
                    modelNameIsCorrectly = false;
                } else {
                    modelNameIsCorrectly = true;
                    if (manufacturerNameIsCorrectly && modelNameIsCorrectly && engineCapacityIsCorrectly
                            && seatingIsCorrectly ) {
                        addButton.setEnabled(true);
                    }

                }
            }


        });
        engineCapacityView.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(s.toString().trim().length()==0){
                    addButton.setEnabled(false);
                    engineCapacityIsCorrectly = false;
                } else {
                    engineCapacityIsCorrectly = true;
                    if (manufacturerNameIsCorrectly && modelNameIsCorrectly && engineCapacityIsCorrectly
                            && seatingIsCorrectly ) {
                        addButton.setEnabled(true);
                    }

                }
            }


        });
        seatingView.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(s.toString().trim().length()==0){
                    addButton.setEnabled(false);
                    seatingIsCorrectly = false;
                } else {
                    seatingIsCorrectly = true;
                    if (manufacturerNameIsCorrectly && modelNameIsCorrectly && engineCapacityIsCorrectly
                            && seatingIsCorrectly ) {
                        addButton.setEnabled(true);
                    }

                }
            }


        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model);
        inti();
    }

    public void onClickAddModel(View view) {

        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    dataSource.addCarModle(new CarModel(manufacturerNameView.getText().toString(),
                            modelNameView.getText().toString(),
                            Integer.parseInt(engineCapacityView.getText().toString()),
                            CarModel.GEAR_BOX.valueOf(gearBoxSpinnerView.getSelectedItem().toString()),
                            Integer.parseInt(seatingView.getText().toString())));

                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();

        finish();

    }
}