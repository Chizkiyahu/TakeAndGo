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

    protected EditText customer_add_id, customer_add_first_name, customer_add_last_name,customer_add_phone,customer_add_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        inti();
    }

    void inti(){
        customer_add_id = findViewById(R.id.customer_add_id);
        customer_add_first_name = findViewById(R.id.customer_add_first_name);
        customer_add_last_name = findViewById(R.id.customer_add_last_name);
        customer_add_phone = findViewById(R.id.customer_add_phone);
        customer_add_email = findViewById(R.id.customer_add_email);
        customer_add_email = findViewById(R.id.customer_add_email);
    }


    public void onClickAddCustomer(View view) {
        try {
            BackendFactory.getDataSource().addCustomer(new Customer(customer_add_last_name.getText().toString(),
                    customer_add_first_name.getText().toString(),
                    Integer.parseInt( customer_add_id.getText().toString()),
                     Long.parseLong(customer_add_phone.getText().toString()),
                    customer_add_email.getText().toString()));

        }
        catch (Exception e){
            Log.e(Constants.Log.TAG,e.getMessage());
        }

        startActivity(new Intent(CustomerActivity.this, CustomersActivity.class));
    }
}
