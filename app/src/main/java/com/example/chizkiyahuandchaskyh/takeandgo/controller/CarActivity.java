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
import com.example.chizkiyahuandchaskyh.takeandgo.model.entities.Car;
import com.example.chizkiyahuandchaskyh.takeandgo.model.entities.Customer;

import java.util.ArrayList;

public class CarActivity extends AppCompatActivity {

    protected ArrayList<Car> carsArrayList = new ArrayList<>();
    protected ArrayAdapter<Car> carArrayAdapter = null;
    protected ListView list_car;
    protected EditText car_line_id, car_line_branch_id,car_line_km ,car_line_model_id;
    protected DataSource dataSource  = BackendFactory.getDataSource();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);
        Init();
        CreateAdapter();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_car, menu);
        return true;
    }

    public void onClickAddCar(MenuItem item) {
        startActivity(new Intent(this, AddCarActivity.class));
    }

    private void CreateAdapter() {
        carArrayAdapter = new ArrayAdapter<Car>( this, R.layout.car_line, dataSource.getCarList()) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


                if (convertView == null) {
                    convertView = View.inflate( CarActivity.this,R.layout.car_line,null );
                }

                Car car = this.getItem(position);
                TextView car_line_id = convertView.findViewById( R.id.car_line_id );
                TextView car_line_branch_id = convertView.findViewById( R.id.car_line_branch_id );
                TextView car_line_km = convertView.findViewById( R.id.car_line_km );
                TextView car_line_model_id = convertView.findViewById( R.id.car_line_model_id );




                car_line_id.setText("Car number :"  + car.getId() );
                car_line_branch_id.setText("Branch ID :" + car.getBranchID() );
                car_line_km.setText("KM : " + car.getKm() );
                car_line_model_id.setText("Model ID : " + car.getModelID() );
                return convertView;
            }
        };

        list_car.setAdapter( carArrayAdapter );


    }

    void  Init(){
        list_car = findViewById(R.id.list_cars);
        setTitle("Cars");

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
    }
}