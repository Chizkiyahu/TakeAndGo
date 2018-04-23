package com.example.chizkiyahuandchaskyh.takeandgo.controller;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.chizkiyahuandchaskyh.takeandgo.R;
import com.example.chizkiyahuandchaskyh.takeandgo.model.entities.Car;

public class CarsActivity extends ListViewBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected ArrayAdapter getListViewAdapter() {
        return new ArrayAdapter<Car>( this, R.layout.car_line, dataSource.getCarList()) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


                if (convertView == null) {
                    convertView = View.inflate( CarsActivity.this,R.layout.car_line,null );
                }

                Car car = this.getItem(position);
                TextView idView = convertView.findViewById( R.id.car_line_id );
                TextView branchIdView = convertView.findViewById( R.id.car_line_branch_id );
                TextView kmView = convertView.findViewById( R.id.car_line_km );
                TextView modelIdView = convertView.findViewById( R.id.car_line_model_id );

                idView.setText("Car number:"  + car.getId() );
                branchIdView.setText("Branch ID:" + car.getBranchID() );
                kmView.setText("KM: " + car.getKm() );
                modelIdView.setText("Model ID: " + car.getModelID() );
                return convertView;
            }
        };
    }

    @Override
    protected void onClickCreateNew() {
        startActivity(new Intent(this, CarActivity.class));
    }

    @Override
    protected String getActivityTitle() {
        return getString(R.string.cars);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
    }
}