package com.example.chizkiyahuandchaskyh.takeandgo.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.chizkiyahuandchaskyh.takeandgo.R;
import com.example.chizkiyahuandchaskyh.takeandgo.model.datasource.BackendFactory;

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
