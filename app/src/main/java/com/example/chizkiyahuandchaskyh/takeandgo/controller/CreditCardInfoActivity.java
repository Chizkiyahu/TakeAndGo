package com.example.chizkiyahuandchaskyh.takeandgo.controller;

import android.app.Activity;
import android.content.Intent;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.example.chizkiyahuandchaskyh.takeandgo.R;
import com.example.chizkiyahuandchaskyh.takeandgo.model.entities.CreditCard;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class CreditCardInfoActivity extends AppCompatActivity {

    Spinner issuerSpinnerView;
    EditText digitsView, cvvView, expirationView;
    Button doneBtn, validateBtn;

    HashMap<String, CreditCard.Issuer> issuerHashMap;

    static public final String CREDIT_CARD_EXTRA_DATA = "CreditCardVal";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_card_info);
        init();
    }

    private void init() {
        issuerSpinnerView = findViewById(R.id.credit_card_issuer);
        digitsView = findViewById(R.id.credit_card_digits);
        cvvView = findViewById(R.id.credit_card_cvv);
        expirationView = findViewById(R.id.credit_card_expiration);
        doneBtn = findViewById(R.id.credit_card_done_btn);
        validateBtn = findViewById(R.id.credit_card_validate_btn);

        final ArrayList<String> issuers = new ArrayList<String>();
        issuerHashMap = new HashMap<>();
        for (CreditCard.Issuer issuer : CreditCard.Issuer.values()) {
            issuers.add(issuer.toString());
            issuerHashMap.put(issuer.toString(), issuer);
        }

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, issuers);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        issuerSpinnerView.setAdapter(spinnerArrayAdapter);
    }

    public void onClickValidateBtn(View view) {
        CreditCard creditCard = getCreditCard();
        if(creditCard != null && creditCard.isValid()) {
            validateBtn.setBackgroundColor(Color.GREEN);
        }
        else {
            validateBtn.setBackgroundColor(Color.RED);
        }
    }

    public void onClickDoneBtn(View view) {
        CreditCard creditCard = getCreditCard();
        if(creditCard != null && creditCard.isValid()) {
            Intent result = new Intent();
            result.putExtra(CREDIT_CARD_EXTRA_DATA, creditCard);
            setResult(Activity.RESULT_OK, result);
            finish();
        }
    }

    public void onClickCancelBtn(View view) {
        setResult(Activity.RESULT_CANCELED);
        finish();
    }

    private CreditCard getCreditCard() {
        CreditCard creditCard = null;
        try {
             creditCard = new CreditCard(digitsView.getText().toString(),
                    issuerHashMap.get(issuerSpinnerView.getSelectedItem().toString()),
                    new Date(expirationView.getText().toString()),
                    cvvView.getText().toString());
        }
        catch (Exception ex) {
            creditCard = null;
        }
        return creditCard;
    }
}
