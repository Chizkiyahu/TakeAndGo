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

public class CustomersActivity extends ListViewBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void createAdapter() {
        listViewAdapter = new ArrayAdapter<Customer>( this, R.layout.customer_line, dataSource.getListCustomer()) {
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
    }


    @Override
    protected void onClickCreateNew() {
        startActivity(new Intent(this, AddCustomerActivity.class));
    }

    @Override
    protected String getActivityTitle() {
        return getString(R.string.Customers);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
    }
}
