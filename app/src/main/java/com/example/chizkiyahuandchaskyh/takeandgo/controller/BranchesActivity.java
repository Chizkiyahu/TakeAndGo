package com.example.chizkiyahuandchaskyh.takeandgo.controller;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.chizkiyahuandchaskyh.takeandgo.R;
import com.example.chizkiyahuandchaskyh.takeandgo.model.entities.Branch;

import java.util.ArrayList;

public class BranchesActivity extends ListViewBaseActivity {

    ArrayList<Branch> branchArrayList = new ArrayList<>();
    ArrayAdapter<Branch> adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        new  AsyncTask<Void, Void, Void>(){

            @Override
            protected void onPostExecute(Void aVoid) {

                getListViewAdapter().clear();
                getListViewAdapter().addAll(branchArrayList);
                getListViewAdapter().notifyDataSetChanged();
            }

            @Override
            protected Void doInBackground(Void... voids) {
                branchArrayList = new ArrayList<>(dataSource.getBranchList());
                return null;
            }

        }.execute();

    }

    @Override
    protected ArrayAdapter getListViewAdapter() {
        if(adapter == null) {
            adapter =  new ArrayAdapter<Branch>(this, R.layout.branch_line, branchArrayList) {
                @NonNull
                @Override
                public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


                    if (convertView == null) {
                        convertView = View.inflate(BranchesActivity.this, R.layout.branch_line, null);
                    }

                    Branch branch = this.getItem(position);

                    TextView addressView = convertView.findViewById(R.id.branch_line_address);
                    TextView numberOfParkingSpacesView = convertView.findViewById(R.id.branch_line_num_parking_spaces);
                    TextView branchIDView = convertView.findViewById(R.id.branch_line_id);

                    addressView.setText("Address: " + branch.getAddress().toString());
                    numberOfParkingSpacesView.setText("Parking spaces:" + branch.getNumParkingSpaces());
                    branchIDView.setText("Branch ID: " + branch.getId());

                    return convertView;
                }


            };
        }
        return adapter;
    }

    @Override
    protected void onClickCreateNew() {
        startActivity(new Intent(this, BranchActivity.class));
    }

    @Override
    protected String getActivityTitle() {
        return getString(R.string.branches);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
    }


    void showMass(String mass){
        Snackbar.make(getCurrentFocus(), mass, Snackbar.LENGTH_SHORT).show();
    }


}

