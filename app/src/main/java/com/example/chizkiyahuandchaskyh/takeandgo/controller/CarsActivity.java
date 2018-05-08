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
import com.example.chizkiyahuandchaskyh.takeandgo.model.entities.Car;
import com.example.chizkiyahuandchaskyh.takeandgo.model.entities.CarModel;

import java.util.ArrayList;

public class CarsActivity extends ListViewBaseActivity {

    ArrayList<Car> carArrayList = new ArrayList<>();
    ArrayAdapter<Car> adapter = null;


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
                getListViewAdapter().addAll(carArrayList);
                getListViewAdapter().notifyDataSetChanged();
            }

            @Override
            protected Void doInBackground(Void... voids) {
                carArrayList = new ArrayList<>(dataSource.getCarList());
                return null;
            }

        }.execute();

    }

    @Override
    protected ArrayAdapter getListViewAdapter() {
        if (adapter == null)
            return new ArrayAdapter<Car>( this, R.layout.car_line, carArrayList) {
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
        return adapter;
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