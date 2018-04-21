package com.example.chizkiyahuandchaskyh.takeandgo.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.chizkiyahuandchaskyh.takeandgo.R;
import com.example.chizkiyahuandchaskyh.takeandgo.model.beckend.BackendFactory;
import com.example.chizkiyahuandchaskyh.takeandgo.model.entities.Customer;
import com.example.chizkiyahuandchaskyh.takeandgo.model.utils.Constants;

public class CustomerActivity extends AppCompatActivity {

    protected EditText idView, firstNameView, lastNameView, phoneView, emailView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        inti();
    }

    void inti(){
        idView = findViewById(R.id.customer_add_id);
        firstNameView = findViewById(R.id.customer_add_first_name);
        lastNameView = findViewById(R.id.customer_add_last_name);
        phoneView = findViewById(R.id.customer_add_phone);
        emailView = findViewById(R.id.customer_add_email);
        emailView = findViewById(R.id.customer_add_email);
    }


    public void onClickAddCustomer(View view) {
        try {
            BackendFactory.getDataSource().addCustomer(new Customer(lastNameView.getText().toString(),
                    firstNameView.getText().toString(),
                    Integer.parseInt( idView.getText().toString()),
                     Long.parseLong(phoneView.getText().toString()),
                    emailView.getText().toString()));

        }
        catch (Exception e){
            Log.e(Constants.Log.TAG,e.getMessage());
        }

        startActivity(new Intent(CustomerActivity.this, CustomersActivity.class));
    }
}
