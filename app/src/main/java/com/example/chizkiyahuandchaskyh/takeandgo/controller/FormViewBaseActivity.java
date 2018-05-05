package com.example.chizkiyahuandchaskyh.takeandgo.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.example.chizkiyahuandchaskyh.takeandgo.R;

public class FormViewBaseActivity extends AppCompatActivity {

    protected LinearLayout fieldsLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_view_base);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_form_veiw_base, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                return handleBackButtonClick();
            case R.id.done_btn:
                return handleDoneButtonClick();
        }
        return super.onOptionsItemSelected(item);
    }

    protected void init() {

    }

    protected void addView(int id, String label, String placeholder, int inputType, boolean editable, CustomFormFieldView.CustomFormFieldValidation validator) {
        CustomFormFieldView view = new CustomFormFieldView(this, null);
        view.setId(id);
        view.setLabel(label);
        view.setPlaceholder(placeholder);
        view.setInputType(inputType);
        view.setEditable(editable);
        view.setValidator(validator);
        fieldsLayout.addView(view);
    }

    protected void clearViews() {
        fieldsLayout.removeAllViews();
    }

    protected Boolean handleBackButtonClick() {
        finish();
        return true;
    }

    protected Boolean handleDoneButtonClick() {
        return true;
    }

    @Override
    public void onBackPressed() {
        handleBackButtonClick();
    }
}
