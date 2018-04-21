package com.example.chizkiyahuandchaskyh.takeandgo.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.chizkiyahuandchaskyh.takeandgo.R;
import com.example.chizkiyahuandchaskyh.takeandgo.model.beckend.BackendFactory;
import com.example.chizkiyahuandchaskyh.takeandgo.model.entities.Address;
import com.example.chizkiyahuandchaskyh.takeandgo.model.entities.Branch;
import com.example.chizkiyahuandchaskyh.takeandgo.model.utils.Constants;

public class AddBranchActivity extends AppCompatActivity {

    protected EditText branch_country, branch_city, branch_street, branch_houseNum, branch_num_parking_spaces;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_branch);
        inti();
    }

    public void onClickAdd(View view)  {
        try {
            BackendFactory.getDataSource().addBranch(
                    new Branch(Integer.parseInt(branch_num_parking_spaces.getText().toString()),
                            new Address(branch_country.getText().toString(),
                                    branch_city.getText().toString(),
                                    branch_street.getText().toString(),
                                    Integer.parseInt(branch_houseNum.getText().toString()))));

        }
        catch (Exception e){
            Log.e(Constants.Log.TAG,e.getMessage());
        }

        startActivity(new Intent(AddBranchActivity.this, BranchesActivity.class));
    }

    void inti(){
        branch_country = findViewById(R.id.branch_country);
        branch_city = findViewById(R.id.branch_city);
        branch_street = findViewById(R.id.branch_street);
        branch_houseNum = findViewById(R.id.branch_houseNum);
        branch_num_parking_spaces = findViewById(R.id.branch_num_parking_spaces);
    }


}
