package com.example.chizkiyahuandchaskyh.takeandgo.controller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.chizkiyahuandchaskyh.takeandgo.R;
import com.example.chizkiyahuandchaskyh.takeandgo.model.utils.Test;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkLogin();
    }

    void checkLogin(){

        SharedPreferences prefs = getSharedPreferences("UserData", MODE_PRIVATE);
        if(! prefs.getBoolean("isLogon", false)){
            startActivity(new Intent( MainActivity.this, LoginActivity.class));

        }
    }

    void logOutFun(View view){
        SharedPreferences prefs = getSharedPreferences("UserData", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("isLogon", false);
        editor.putInt("failedLogin", 0);
        editor.commit();
        checkLogin();
    }


    public void onClickBranchs(View view) {
        startActivity(new Intent(MainActivity.this, BranchsActivity.class));
    }

    public void onClickCustomers(View view) {
    }
}
