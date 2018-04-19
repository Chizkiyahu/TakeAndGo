package com.example.chizkiyahuandchaskyh.takeandgo.controller;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.chizkiyahuandchaskyh.takeandgo.R;
import com.example.chizkiyahuandchaskyh.takeandgo.model.beckend.BackendFactory;
import com.example.chizkiyahuandchaskyh.takeandgo.model.beckend.DataSource;
import com.example.chizkiyahuandchaskyh.takeandgo.model.entities.Branch;
import com.example.chizkiyahuandchaskyh.takeandgo.model.entities.Customer;

import java.util.ArrayList;

public class CustomersActivity extends AppCompatActivity {


    protected ArrayList<Customer> customerArrayList = new ArrayList<>();
    protected ArrayAdapter<Customer> customeArrayAdapter = null;
    protected ListView list_customer;
    protected EditText customer_line_id, customer_line_lastName, customer_line_firstName,customer_line_phoneNumber,customer_line_email;
    protected DataSource dataSource  = BackendFactory.getDataSource();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customers);
        Init();
        CreateAdapter();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_customers, menu);
        return true;
    }

    public void onClickAddCustomer(MenuItem item) {
        startActivity(new Intent(this, AddCustomerActivity.class));
    }

    private void CreateAdapter() {
        customeArrayAdapter = new ArrayAdapter<Customer>( this, R.layout.customer_line, dataSource.getListCustomer()) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


                if (convertView == null) {
                    convertView = View.inflate( CustomersActivity.this,R.layout.customer_line,null );
                }

                Customer customer = this.getItem(position);
                TextView customer_line_id = convertView.findViewById( R.id.customer_line_id );
                TextView customer_line_lastName = convertView.findViewById( R.id.customer_line_lastName );
                TextView customer_line_firstName = convertView.findViewById( R.id.customer_line_firstName );
                TextView customer_line_phoneNumber = convertView.findViewById( R.id.customer_line_phoneNumber );
                TextView customer_line_email = convertView.findViewById( R.id.customer_line_email );



                customer_line_id.setText("ID :"  + customer.getId() );
                customer_line_lastName.setText("Last Name :" + customer.getLastName() );
                customer_line_firstName.setText("First Name : " + customer.getFirstName() );
                customer_line_phoneNumber.setText("Phone : " + customer.getPhoneNumber() );
                customer_line_email.setText("EMAIL : " + customer.getEmail() );
                return convertView;
            }
        };

        list_customer.setAdapter( customeArrayAdapter );


    }

    void  Init(){
        list_customer = findViewById(R.id.list_customers);
        setTitle("Customer");

    }

}
