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
import com.example.chizkiyahuandchaskyh.takeandgo.model.entities.CarModel;

public class ModelsActivity extends ListViewBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected ArrayAdapter getListViewAdapter() {
        return new ArrayAdapter<CarModel>( this, R.layout.model_line, dataSource.getCarModelList()) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


                if (convertView == null) {
                    convertView = View.inflate( ModelsActivity.this,R.layout.model_line,null );
                }

                CarModel carModel = this.getItem(position);
                TextView idView = convertView.findViewById( R.id.model_line_id );
                TextView manufacturerNameView = convertView.findViewById( R.id.model_line_manufacturer_name );
                TextView modelNameView = convertView.findViewById( R.id.model_line_model_name );
                TextView engineCapacityView = convertView.findViewById( R.id.model_line_engine_capacity );
                TextView seatingView = convertView.findViewById( R.id.model_line_seating );
                TextView gearBoxView = convertView.findViewById( R.id.model_line_gear_box );

                idView.setText("Model ID: " + carModel.getCodeModel() );
                manufacturerNameView.setText("Manufacturer: " + carModel.getManufacturerName() );
                modelNameView.setText("Model Name: " + carModel.getCodeModel());
                engineCapacityView.setText("Engine Capacity: " + carModel.getEngineCapacity());
                seatingView.setText("Seating: " +  carModel.getSeating());
                gearBoxView.setText("Gear Box: " +  carModel.getGearBox().name().toString().toLowerCase());

                return convertView;
            }
        };
    }

    @Override
    protected void onClickCreateNew() {
        startActivity(new Intent(this, ModelActivity.class));
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
