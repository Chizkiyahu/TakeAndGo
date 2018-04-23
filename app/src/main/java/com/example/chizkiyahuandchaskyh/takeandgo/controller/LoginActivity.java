package com.example.chizkiyahuandchaskyh.takeandgo.controller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.chizkiyahuandchaskyh.takeandgo.R;
import com.example.chizkiyahuandchaskyh.takeandgo.model.beckend.BackendFactory;
import com.example.chizkiyahuandchaskyh.takeandgo.model.beckend.DataSource;
import com.example.chizkiyahuandchaskyh.takeandgo.model.utils.Constants;


public class LoginActivity extends AppCompatActivity {

    EditText email, password;
    DataSource dataSource = BackendFactory.getDataSource();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

    }

    void onClickSignIn(View view){
        try {
            String user = email.getText().toString();
            String pass =  password.getText().toString();

           if( tryUserPass(user,pass)){
               startActivity(new Intent(this, MainActivity.class));
           }
           else {
               throw new Exception("Error: Username or password is incorrect");
           }
        }catch (Exception e){
            Snackbar.make(view, e.getMessage(), Snackbar.LENGTH_SHORT).show();
            Log.e(Constants.Log.TAG,e.getMessage());
        }

    }

    void onClickRegister(View  view){

        String user = email.getText().toString();
        String pass =  password.getText().toString();
        try {
            if (!isEmailValid(user)){
                throw new Exception("Please enter a valid email address");
            }
            if(!isPassStrong(pass)){
                throw new Exception("Password is too short Please enter at least 8 characters");
            }
            if(dataSource.checkUserIsFree(user)){
                dataSource.addUserPass(user, pass);
                onClickSignIn(view);
            }else {
                throw new Exception("Error: the user aldeady exsit");
            }

        }catch (Exception e){
            Snackbar.make(view, e.getMessage(), Snackbar.LENGTH_SHORT).show();
            Log.e(Constants.Log.TAG,e.getMessage());
        }
    }

    boolean tryUserPass(String username, String password) throws Exception{

        SharedPreferences prefs = getSharedPreferences("UserData", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        if(dataSource.TryUserPass(username, password)){
            editor.putBoolean("isLogon", true);
            editor.putInt("failedLogin", 0);
            editor.commit();
            return true;
        }
        editor.putInt("failedLogin", prefs.getInt("failedLogin",0 ) + 1);
        return false;
    }

    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    boolean isPassStrong(String pass) {
        if (pass.length() < 8){
            return  false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
    }


}
