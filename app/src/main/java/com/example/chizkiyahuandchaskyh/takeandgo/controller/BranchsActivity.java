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

import java.util.ArrayList;

public class BranchsActivity extends AppCompatActivity {


    protected ArrayList<Branch> branchArrayList = new ArrayList<>();
    protected ArrayAdapter<Branch> branchArrayAdapter = null;
    protected ListView list_branchs;
    protected EditText branch_line_address,branch_line_num_parking_spaces,branch_line_id;
    protected DataSource dataSource  = BackendFactory.getDataSource();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branchs);
        Init();
        CreateAdapter();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_branchs, menu);
        return true;
    }


    public void onClickAddBranch(MenuItem item) {
        startActivity(new Intent(this, AddBranchActivity.class));
    }

    private void CreateAdapter() {
        branchArrayAdapter = new ArrayAdapter<Branch>( this, R.layout.branch_line, dataSource.getBranchList()) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


                if (convertView == null) {
                    convertView = View.inflate( BranchsActivity.this,R.layout.branch_line,null );
                }

                Branch branch = this.getItem(position);
                TextView branch_line_address = convertView.findViewById( R.id.branch_line_address );
                TextView branch_line_num_parking_spaces = convertView.findViewById( R.id.branch_line_num_parking_spaces );
                TextView branch_line_id = convertView.findViewById( R.id.branch_line_id );


                branch_line_address.setText( "Address: " +  branch.getAddress().getStreet() + " " +
                        branch.getAddress().getHouseNum() + " "  +
                        branch.getAddress().getCity().toString() + " " +
                        branch.getAddress().getCountry());
                branch_line_num_parking_spaces.setText(" Parking spaces :"  +branch.getNumParkingSpaces() );
                branch_line_id.setText( " Branch ID: " + branch.getId() );
                return convertView;
            }
        };

        list_branchs.setAdapter( branchArrayAdapter );


    }

    void  Init(){
        list_branchs = findViewById(R.id.list_branchs);
        setTitle("Branchs");

    }

}
