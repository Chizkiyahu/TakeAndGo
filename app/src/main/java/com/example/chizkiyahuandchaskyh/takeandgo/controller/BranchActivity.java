package com.example.chizkiyahuandchaskyh.takeandgo.controller;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.chizkiyahuandchaskyh.takeandgo.R;
import com.example.chizkiyahuandchaskyh.takeandgo.model.beckend.BackendFactory;
import com.example.chizkiyahuandchaskyh.takeandgo.model.entities.Address;
import com.example.chizkiyahuandchaskyh.takeandgo.model.entities.Branch;
import com.example.chizkiyahuandchaskyh.takeandgo.model.utils.Constants;

public class BranchActivity extends AppCompatActivity {

    protected EditText countryView, cityView, streetView, houseNumberView, numberOfParkingSpacesView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branch);
        init();
    }

    @SuppressLint("StaticFieldLeak")
    public void onClickAdd(final View view)  {
        try {

            final Branch newBranch = new Branch(Integer.parseInt(numberOfParkingSpacesView.getText().toString()),
                                    new Address(countryView.getText().toString(),
                                        cityView.getText().toString(),
                                        streetView.getText().toString(),
                                        Integer.parseInt(houseNumberView.getText().toString())));


            new AsyncTask<Branch,Void , Void>() {
                @Override
                protected Void doInBackground(Branch... branches) {
                    try {
                        BackendFactory.getDataSource().addBranch(branches[0]);

                    }catch (Exception e){
                            Log.e(Constants.Log.TAG,e.getMessage());
                    }
                    return null;
                }
            }.execute(newBranch);


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
