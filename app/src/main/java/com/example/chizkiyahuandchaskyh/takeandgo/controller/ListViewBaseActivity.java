package com.example.chizkiyahuandchaskyh.takeandgo.controller;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.chizkiyahuandchaskyh.takeandgo.R;
import com.example.chizkiyahuandchaskyh.takeandgo.model.beckend.BackendFactory;
import com.example.chizkiyahuandchaskyh.takeandgo.model.beckend.DataSource;

public class ListViewBaseActivity extends AppCompatActivity {


    protected DataSource dataSource  = BackendFactory.getDataSource();
    protected ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_base);
        init();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               onClickCreateNew();
            }
        });
    }

    private void init(){
        listView = findViewById(R.id.list_view);
        setTitle(getActivityTitle());
        getListViewAdapter();
        listView.setAdapter(getListViewAdapter());
    }

    protected ArrayAdapter getListViewAdapter() {
        return null;
    }

    protected String getActivityTitle() {
        return "";
    }

    protected void onClickCreateNew() {

    }

}
