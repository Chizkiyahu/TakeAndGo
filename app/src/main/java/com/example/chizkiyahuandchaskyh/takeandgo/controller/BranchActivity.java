package com.example.chizkiyahuandchaskyh.takeandgo.controller;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.chizkiyahuandchaskyh.takeandgo.R;
import com.example.chizkiyahuandchaskyh.takeandgo.model.beckend.BackendFactory;
import com.example.chizkiyahuandchaskyh.takeandgo.model.beckend.DataSource;
import com.example.chizkiyahuandchaskyh.takeandgo.model.entities.Address;
import com.example.chizkiyahuandchaskyh.takeandgo.model.entities.Branch;
import com.example.chizkiyahuandchaskyh.takeandgo.model.utils.Constants;

public class BranchActivity extends AppCompatActivity {

    protected EditText countryView, cityView, streetView, houseNumberView, numberOfParkingSpacesView;
    protected DataSource dataSource = BackendFactory.getDataSource();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branch);
        init();
    }

    @SuppressLint("StaticFieldLeak")
    public void onClickAdd(final View view)  {
        try {
            new AsyncTask<Void,String , Void>() {
                @Override
                protected Void doInBackground(Void... voids) {
                    try {
                        dataSource.addBranch(new Branch(Integer.parseInt(numberOfParkingSpacesView.getText().toString()),
                                new Address(countryView.getText().toString(),
                                        cityView.getText().toString(),
                                        streetView.getText().toString(),
                                        Integer.parseInt(houseNumberView.getText().toString()))));

                    }catch (Exception e){
                        Log.e(Constants.Log.TAG,e.getMessage());
                        Snackbar.make(getWindow().getDecorView().getRootView(), "Could not create Customer.", Snackbar.LENGTH_SHORT).show();

                    }
                    return null;
                }
            }.execute();
        }
        catch (Exception e){
            Log.e(Constants.Log.TAG,e.getMessage());
        }

        finish();
    }

    void init(){
        countryView = findViewById(R.id.branch_country);
        cityView = findViewById(R.id.branch_city);
        streetView = findViewById(R.id.branch_street);
        houseNumberView = findViewById(R.id.branch_houseNum);
        numberOfParkingSpacesView = findViewById(R.id.branch_num_parking_spaces);


    }

}


