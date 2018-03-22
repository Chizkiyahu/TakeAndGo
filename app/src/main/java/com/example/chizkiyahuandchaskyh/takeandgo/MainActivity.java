package com.example.chizkiyahuandchaskyh.takeandgo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import model.datasource.BackendFactory;
import model.datasource.DatabaseList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BackendFactory backendFactory = new BackendFactory();
        setContentView(R.layout.activity_main);
        if (!backendFactory.getInstance().logon()){
           LoginActivity login = new LoginActivity();
            Intent intent = new Intent(this, LoginActivity.class);

            startActivity(intent);
        }














    }
}
