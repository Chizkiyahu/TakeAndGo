package com.example.chizkiyahuandchaskyh.takeandgo.controller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.chizkiyahuandchaskyh.takeandgo.R;
import com.example.chizkiyahuandchaskyh.takeandgo.model.beckend.BackendFactory;
import com.example.chizkiyahuandchaskyh.takeandgo.model.utils.Constants;


public class LoginActivity extends AppCompatActivity {

    EditText email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

    }

    void emailSignInFun(View view){
        try {

           if( tryUserPass(email.getText().toString(),password.getText().toString())){
               startActivity(new Intent(this, MainActivity.class));
           }
        }catch (Exception e){
            Log.e(Constants.Log.TAG,e.getMessage());
        }

    }

    void registerFun(View  view){
        try {
            if(BackendFactory.getDataSource().checkUserIsFree(email.getText().toString())){
                BackendFactory.getDataSource().addUserPass(email.getText().toString(), password.getText().toString());
                emailSignInFun(view);
            }else {
                //error the user aldeady exsit
            }
        }catch (Exception e){
            Log.e(Constants.Log.TAG,e.getMessage());
        }
    }

    boolean tryUserPass(String username, String password) throws Exception{

        SharedPreferences prefs = getSharedPreferences("UserData", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        if(BackendFactory.getDataSource().TryUserPass(username, password)){
            editor.putBoolean("isLogon", true);
            editor.putInt("failedLogin", 0);
            editor.commit();
            return true;
        }
        editor.putInt("failedLogin", prefs.getInt("failedLogin",0 ) + 1);
        return false;
    }


}
