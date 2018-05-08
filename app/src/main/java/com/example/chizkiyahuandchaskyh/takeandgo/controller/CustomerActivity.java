package com.example.chizkiyahuandchaskyh.takeandgo.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.chizkiyahuandchaskyh.takeandgo.R;
import com.example.chizkiyahuandchaskyh.takeandgo.model.beckend.BackendFactory;
import com.example.chizkiyahuandchaskyh.takeandgo.model.entities.CreditCard;
import com.example.chizkiyahuandchaskyh.takeandgo.model.entities.Customer;
import com.example.chizkiyahuandchaskyh.takeandgo.model.utils.Constants;

public class CustomerActivity extends AppCompatActivity {

    protected EditText idView, firstNameView, lastNameView, phoneView, emailView;
    protected TextView creditCardDigits;
    protected Button addCreditCardBtn;
    protected LinearLayout creditCardLayout;

    private final int GET_CREDIT_CARD_INFO_CODE = 123456;

    CreditCard creditCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        init();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                handleCreditCardResult(resultCode, data);
                return;
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void handleCreditCardResult(int resultCode, Intent data) {
        switch (resultCode) {
            case Activity.RESULT_OK:
                creditCard = (CreditCard) data.getSerializableExtra(CreditCardInfoActivity.CREDIT_CARD_EXTRA_DATA);
                if(creditCard != null) {
                    String digits = creditCard.getDigits();
                    creditCardDigits.setText("**********" + digits.substring(digits.length() - 4, digits.length()));
                    refreshViews();
                }
            case 0:
            default:
                break;
        }
    }

    void init(){
        idView = findViewById(R.id.customer_add_id);
        firstNameView = findViewById(R.id.customer_add_first_name);
        lastNameView = findViewById(R.id.customer_add_last_name);
        phoneView = findViewById(R.id.customer_add_phone);
        emailView = findViewById(R.id.customer_add_email);
        addCreditCardBtn = findViewById(R.id.customer_credit_card_btn);
        creditCardDigits = findViewById(R.id.customer_credit_card_digits);
        creditCardLayout = findViewById(R.id.customer_credit_card_layout);
    }


    public void onClickAddCustomer(View view) {
        Customer customer = null;
        try {
             customer = new Customer(lastNameView.getText().toString(),
                    firstNameView.getText().toString(),
                    Integer.parseInt( idView.getText().toString()),
                    Long.parseLong(phoneView.getText().toString()),
                    emailView.getText().toString());
        }
        catch (Exception e){
            Log.e(Constants.Log.TAG,e.getMessage());
            customer = null;
        }

        if(customer != null) {
            customer.setCreditCard(creditCard);
            new AsyncTask<Customer,Void,Void>(){
                @Override
                protected Void doInBackground(Customer... customers) {
                    try {
                        BackendFactory.getDataSource().addCustomer(customers[0]);
                    } catch (Exception ex) {
                            Log.e(Constants.Log.TAG,ex.getMessage());
                    }
                    return null;
                }
            }.execute(customer);
            finish();
        }
        else {
            Snackbar.make(view, "Could not create Customer.", Snackbar.LENGTH_SHORT).show();
        }
    }

    public void onClickAddCreditCard(View view) {
        try {
            Intent intent= new Intent(CustomerActivity.this, CreditCardInfoActivity.class);
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent, 1);
        }
        catch (Exception e){
            Log.e(Constants.Log.TAG,e.getMessage());
        }
    }

    public void onClickRemoveCreditCard(View view) {
        creditCard = null;
        refreshViews();
    }

    public void onClickCancel(View view) {
        startActivity(new Intent(CustomerActivity.this, CustomersActivity.class));
    }

    private void refreshViews() {
        if(creditCard == null) {
            addCreditCardBtn.setVisibility(View.VISIBLE);
            creditCardLayout.setVisibility(View.GONE);
        }
        else {
            addCreditCardBtn.setVisibility(View.GONE);
            creditCardLayout.setVisibility(View.VISIBLE);
        }
    }
}
