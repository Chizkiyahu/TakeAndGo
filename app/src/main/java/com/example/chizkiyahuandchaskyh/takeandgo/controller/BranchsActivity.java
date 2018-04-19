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
    protected ArrayList<Branch>  testBranchArrayList = new ArrayList<>();
    protected ArrayAdapter<Branch> branchArrayAdapter = null;
    protected ListView list_branchs;
    protected EditText branch_line_city,branch_line_street,branch_line_id;
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

                Branch branch = (Branch) this.getItem(position);
                TextView branch_line_city = (TextView) convertView.findViewById( R.id.branch_line_city );
                TextView branch_line_street = (TextView) convertView.findViewById( R.id.branch_line_street );
                TextView branch_line_id = (TextView) convertView.findViewById( R.id.branch_line_id );


                branch_line_city.setText( "City :" + branch.getAddress().getCity().toString() );
                branch_line_street.setText( "km: " + branch.getAddress().getStreet().toString() );
                branch_line_id.setText( ",Total Seats:" + branch.getId() );
                return convertView;
            }
        };

        list_branchs.setAdapter( branchArrayAdapter );


    }

    void  Init(){
        list_branchs = findViewById(R.id.list_branchs);
    }

}
