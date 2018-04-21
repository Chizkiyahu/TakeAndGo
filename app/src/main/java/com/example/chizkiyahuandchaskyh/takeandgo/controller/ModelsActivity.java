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
import com.example.chizkiyahuandchaskyh.takeandgo.model.entities.Branch;
import com.example.chizkiyahuandchaskyh.takeandgo.model.entities.CarModel;

import java.util.ArrayList;
import java.util.List;

public class ModelsActivity extends ListViewBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void createAdapter() {
        listViewAdapter = new ArrayAdapter<CarModel>( this, R.layout.model_line, dataSource.getCarModelList()) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


                if (convertView == null) {
                    convertView = View.inflate( ModelsActivity.this,R.layout.model_line,null );
                }

                CarModel carModel = this.getItem(position);
                TextView model_line_id = convertView.findViewById( R.id.model_line_id );
                TextView model_line_manufacturer_name = convertView.findViewById( R.id.model_line_manufacturer_name );
                TextView model_line_model_name = convertView.findViewById( R.id.model_line_model_name );
                TextView model_line_engine_capacity = convertView.findViewById( R.id.model_line_engine_capacity );
                TextView model_line_seating = convertView.findViewById( R.id.model_line_seating );
                TextView model_line_gear_box = convertView.findViewById( R.id.model_line_gear_box );

                model_line_id.setText("Model ID : " + carModel.getCodeModel() );
                model_line_manufacturer_name.setText("Manufacturer : " + carModel.getManufacturerName() );
                model_line_model_name.setText("Model Name :" + carModel.getCodeModel());
                model_line_engine_capacity.setText("Engine Capacity :" + carModel.getEngineCapacity());
                model_line_seating.setText("Seating :" +  carModel.getSeating());
                model_line_gear_box.setText("Gear Box : " +  carModel.getGearBox().name().toString().toLowerCase());

                return convertView;
            }
        };
    }

    @Override
    protected void onClickCreateNew() {
        startActivity(new Intent(this, AddModelActivity.class));
    }

    @Override
    protected String getActivityTitle() {
        return getString(R.string.car_models);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
    }
}
