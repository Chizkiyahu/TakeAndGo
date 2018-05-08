package com.example.chizkiyahuandchaskyh.takeandgo.controller;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.chizkiyahuandchaskyh.takeandgo.R;
import com.example.chizkiyahuandchaskyh.takeandgo.model.entities.Branch;

import java.util.ArrayList;

public class BranchesActivity extends ListViewBaseActivity {

    ArrayList<Branch> branchArrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onPostResume() {
        new AsyncTask<Void, Void, ArrayList<Branch>>() {
            @Override
            protected ArrayList<Branch> doInBackground(Void... voids) {
                return dataSource.getBranchList();
            }

            @Override
            protected void onPostExecute(ArrayList<Branch> branches) {
                branchArrayList.clear();
                branchArrayList.addAll(branches);
            }
        }.execute(null,null,null);

        super.onPostResume();
    }

    @Override
    protected ArrayAdapter getListViewAdapter() {

        return new ArrayAdapter<Branch>( this, R.layout.branch_line, branchArrayList){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {



                if (convertView == null) {
                    convertView = View.inflate( BranchesActivity.this,R.layout.branch_line,null );
                }

                Branch branch = this.getItem(position);

                TextView addressView = convertView.findViewById( R.id.branch_line_address );
                TextView numberOfParkingSpacesView = convertView.findViewById( R.id.branch_line_num_parking_spaces );
                TextView branchIDView = convertView.findViewById( R.id.branch_line_id );

                addressView.setText( "Address: " +  branch.getAddress().toString());
                numberOfParkingSpacesView.setText("Parking spaces:"  + branch.getNumParkingSpaces() );
                branchIDView.setText( "Branch ID: " + branch.getId() );

                return convertView;
            }



        };

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





}

