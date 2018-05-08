package com.example.chizkiyahuandchaskyh.takeandgo.controller;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.chizkiyahuandchaskyh.takeandgo.R;
import com.example.chizkiyahuandchaskyh.takeandgo.model.beckend.BackendFactory;
import com.example.chizkiyahuandchaskyh.takeandgo.model.beckend.DataSource;
import com.example.chizkiyahuandchaskyh.takeandgo.model.entities.Address;
import com.example.chizkiyahuandchaskyh.takeandgo.model.entities.Branch;
import com.example.chizkiyahuandchaskyh.takeandgo.model.entities.Car;
import com.example.chizkiyahuandchaskyh.takeandgo.model.entities.CarModel;
import com.example.chizkiyahuandchaskyh.takeandgo.model.utils.Constants;

import java.util.ArrayList;

public class CarActivity extends AppCompatActivity {


    protected EditText kmView, idView;
    protected Spinner branchIDView, modelIDView;
    protected DataSource dataSource = BackendFactory.getDataSource();
    protected ArrayList<brancheSpinnerClass> brancheSpinnerClasses = new ArrayList<>();
    protected ArrayList<carModelSpinnerClass> carModelSpinnerClasses = new ArrayList<>();
    protected ArrayAdapter<brancheSpinnerClass> brancheSpinnerClassArrayAdapter;
    protected ArrayAdapter<carModelSpinnerClass> carModelSpinnerArrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);
        inti();
    }

    public void onClickAdd(View view) {

            new AsyncTask<Void,Void, Void>(){

                @Override
                protected Void doInBackground(Void... voids) {
                    try {
                        dataSource.addCar(new Car(Integer.parseInt( idView.getText().toString()),
                                brancheSpinnerClasses.get(branchIDView.getSelectedItemPosition()).getBranch().getId(),
                                Integer.parseInt( kmView.getText().toString()),
                                carModelSpinnerClasses.get(modelIDView.getSelectedItemPosition()).getCarModel()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return null;
                }
            }.execute();

        finish();
    }




    void inti() {
        kmView = findViewById(R.id.add_car_line_km);
        idView = findViewById(R.id.add_car_line_id);
        branchIDView = findViewById(R.id.add_car_line_branch_id);
        modelIDView = findViewById(R.id.add_car_line_model_id);

        new AsyncTask<Void, Void, Void>(){
            @Override
            protected Void doInBackground(Void... voids) {

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (Branch branch : dataSource.getBranchList()) {
                    brancheSpinnerClasses.add(new brancheSpinnerClass(branch));
                }
                for (CarModel carModel : dataSource.getCarModelList()) {
                    carModelSpinnerClasses.add(new carModelSpinnerClass(carModel));
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                brancheSpinnerClassArrayAdapter.notifyDataSetChanged();
                carModelSpinnerArrayAdapter.notifyDataSetChanged();

            }
        }.execute();


        if (brancheSpinnerClassArrayAdapter == null) {
            brancheSpinnerClassArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,brancheSpinnerClasses);
            brancheSpinnerClassArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
            branchIDView.setAdapter(brancheSpinnerClassArrayAdapter);
        }


        if (carModelSpinnerArrayAdapter == null) {
            carModelSpinnerArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,carModelSpinnerClasses);
            carModelSpinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
            modelIDView.setAdapter(carModelSpinnerArrayAdapter);
        }


    }

    private class brancheSpinnerClass  {
        Branch branch;
        public Branch getBranch() {
            return branch;
        }

        public brancheSpinnerClass(Branch branch) {
            this.branch = branch;
        }

        @Override
        public String toString() {
            return branch.getId() + " " + branch.getAddress().getCity() + " " + branch.getAddress().getStreet();
        }
    }

    private class carModelSpinnerClass {

        CarModel carModel;

        public carModelSpinnerClass(CarModel carModel) {
            this.carModel = carModel;
        }

        public CarModel getCarModel() {
            return carModel;
        }

        @Override
        public String toString() {
            return carModel.getManufacturerName() + " " + carModel.getModelName();
        }
    }


}