package com.example.chizkiyahuandchaskyh.takeandgo.controller;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.chizkiyahuandchaskyh.takeandgo.R;
import com.example.chizkiyahuandchaskyh.takeandgo.model.entities.Customer;

import java.util.ArrayList;


public class CustomersActivity extends ListViewBaseActivity {

    protected ArrayList<Customer> customerArrayList = new ArrayList<>();
    protected ArrayAdapter<Customer> adapter = null;


    @Override
    protected void onPostResume() {
        super.onPostResume();
        new AsyncTask<Void, Void, Void>() {


            @Override
            protected void onPostExecute(Void aVoid) {

                getListViewAdapter().clear();
                getListViewAdapter().addAll(customerArrayList);
                getListViewAdapter().notifyDataSetChanged();
            }

            @Override
            protected Void doInBackground(Void... voids) {
                customerArrayList = new ArrayList<>(dataSource.getCustomerList());
                return null;
            }

        }.execute();

    }

    @Override
    protected ArrayAdapter getListViewAdapter() {

        if (adapter ==null) {
            adapter = new ArrayAdapter<Customer>( this, R.layout.customer_line, customerArrayList) {
                @NonNull
                @Override
                public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


                    if (convertView == null) {
                        convertView = View.inflate( CustomersActivity.this,R.layout.customer_line,null );
                    }

                    Customer customer = this.getItem(position);
                    TextView idView = convertView.findViewById( R.id.customer_line_id );
                    TextView lastNameView = convertView.findViewById( R.id.customer_line_lastName );
                    TextView firstNameView = convertView.findViewById( R.id.customer_line_firstName );
                    TextView phoneNumberView = convertView.findViewById( R.id.customer_line_phoneNumber );
                    TextView emailView = convertView.findViewById( R.id.customer_line_email );



                    idView.setText("ID:"  + customer.getId() );
                    lastNameView.setText("Last Name:" + customer.getLastName() );
                    firstNameView.setText("First Name: " + customer.getFirstName() );
                    phoneNumberView.setText("Phone: " + customer.getPhoneNumber() );
                    emailView.setText("Email: " + customer.getEmail() );
                    return convertView;
                }
            };
        }
        return adapter;
    }


    @Override
    protected void onClickCreateNew() {
        startActivity(new Intent(this, CustomerActivity.class));
    }

    @Override
    protected String getActivityTitle() {
        return getString(R.string.customers);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
    }
}
