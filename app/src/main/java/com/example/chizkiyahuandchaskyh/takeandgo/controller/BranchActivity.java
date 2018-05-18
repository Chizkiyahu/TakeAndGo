package com.example.chizkiyahuandchaskyh.takeandgo.controller;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
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
    protected Button addButton;
    protected Boolean countryIsCorrectly = true, cityIsCorrectly = false, streetIsCorrectly = false, houseNumberIsCorrectly = false, numberOfParkingSpacesIsCorrectly = false;
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

                protected void onPostExecute(Void aVoid) {
                    if (!isCancelled())
                        Toast.makeText(BranchActivity.this, R.string.branch_add_succ_mas, Toast.LENGTH_LONG).show();
                        finish();
                }

                @Override
                protected void onProgressUpdate(String... values) {
                    AlertDialog alertDialog = new AlertDialog.Builder(BranchActivity.this).create();
                    alertDialog.setTitle("Error");
                    alertDialog.setMessage(values[0]);
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }

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
                        publishProgress(e.getMessage());
                        cancel(true);


                }
                    return null;
                }
            }.execute();
        }
        catch (Exception e){
            Log.e(Constants.Log.TAG,e.getMessage());
        }

    }

    void init(){
        countryView = findViewById(R.id.branch_country);
        cityView = findViewById(R.id.branch_city);
        streetView = findViewById(R.id.branch_street);
        houseNumberView = findViewById(R.id.branch_houseNum);
        numberOfParkingSpacesView = findViewById(R.id.branch_num_parking_spaces);
        addButton =  findViewById(R.id.branch_add_button);

        addButton.setEnabled(false);
        countryView.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(s.toString().trim().length()==0){
                    addButton.setEnabled(false);
                    countryIsCorrectly = false;
                } else {
                    countryIsCorrectly = true;
                    if (countryIsCorrectly && cityIsCorrectly && streetIsCorrectly && houseNumberIsCorrectly
                            && numberOfParkingSpacesIsCorrectly ) {
                        addButton.setEnabled(true);
                    }

                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        cityView.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(s.toString().trim().length()==0){
                    addButton.setEnabled(false);
                    cityIsCorrectly = false;
                } else {
                    cityIsCorrectly = true;
                    if (countryIsCorrectly && cityIsCorrectly && streetIsCorrectly && houseNumberIsCorrectly
                            && numberOfParkingSpacesIsCorrectly ) {
                        addButton.setEnabled(true);
                    }

                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        streetView.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(s.toString().trim().length()==0){
                    addButton.setEnabled(false);
                    streetIsCorrectly = false;
                } else {
                    streetIsCorrectly = true;
                    if (countryIsCorrectly && cityIsCorrectly && streetIsCorrectly && houseNumberIsCorrectly
                            && numberOfParkingSpacesIsCorrectly ) {
                        addButton.setEnabled(true);
                    }

                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        houseNumberView.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(s.toString().trim().length()==0){
                    addButton.setEnabled(false);
                    houseNumberIsCorrectly = false;
                } else {
                    houseNumberIsCorrectly = true;
                    if (countryIsCorrectly && cityIsCorrectly && streetIsCorrectly && houseNumberIsCorrectly
                            && numberOfParkingSpacesIsCorrectly ) {
                        addButton.setEnabled(true);
                    }

                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        numberOfParkingSpacesView.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(s.toString().trim().length()==0){
                    addButton.setEnabled(false);
                    numberOfParkingSpacesIsCorrectly = false;
                } else {
                    numberOfParkingSpacesIsCorrectly = true;
                    if (countryIsCorrectly && cityIsCorrectly && streetIsCorrectly && houseNumberIsCorrectly
                            && numberOfParkingSpacesIsCorrectly ) {
                        addButton.setEnabled(true);
                    }

                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }



}


