package com.example.chizkiyahuandchaskyh.takeandgo.controller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.chizkiyahuandchaskyh.takeandgo.R;

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
        startActivity(new Intent(MainActivity.this, CustomersActivity.class));
    }

    public void onClickModels(View view) {
        startActivity(new Intent(MainActivity.this, ModelsActivity.class));
    }

    public void onClickCars(View view) {
    }


    public void onClickAdminUsers(View view) {

    }

    @Override
    public void onBackPressed() {
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
    }
}
