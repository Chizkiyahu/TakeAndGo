package com.example.chizkiyahuandchaskyh.takeandgo.controller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.chizkiyahuandchaskyh.takeandgo.R;
import com.example.chizkiyahuandchaskyh.takeandgo.service.findFreeCarService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkLogin();
        startService(new Intent(getBaseContext(), findFreeCarService.class));
    }

    void checkLogin(){

        SharedPreferences prefs = getSharedPreferences("UserData", MODE_PRIVATE);
        if(! prefs.getBoolean("isLogon", false)){
            startActivity(new Intent( MainActivity.this, LoginActivity.class));

        }
    }


    public void onClickBranchs(View view) {
        startActivity(new Intent(MainActivity.this, BranchesActivity.class));
    }

    public void onClickCustomers(View view) {
        startActivity(new Intent(MainActivity.this, CustomersActivity.class));
    }

    public void onClickModels(View view) {
        startActivity(new Intent(MainActivity.this, ModelsActivity.class));
    }

    public void onClickCars(View view) {
        startActivity(new Intent(MainActivity.this, CarsActivity.class));
    }




    @Override
    public void onBackPressed() {
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    public void onClickLogOut(MenuItem item) {
        SharedPreferences prefs = getSharedPreferences("UserData", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("isLogon", false);
        editor.putInt("failedLogin", 0);
        editor.commit();
        checkLogin();
    }
}
