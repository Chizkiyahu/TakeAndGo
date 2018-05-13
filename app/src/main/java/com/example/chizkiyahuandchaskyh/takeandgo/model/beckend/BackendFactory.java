package com.example.chizkiyahuandchaskyh.takeandgo.model.beckend;

import com.example.chizkiyahuandchaskyh.takeandgo.model.datasource.DatabaseList;
import com.example.chizkiyahuandchaskyh.takeandgo.model.datasource.DatabaseSQL;

public class BackendFactory {
    private static final BackendFactory ourInstance = new BackendFactory();

    static BackendFactory getInstance() {
        return ourInstance;
    }

    //static DataSource dataSourceInstance = new DatabaseList();
    static DataSource dataSourceInstance = new DatabaseSQL();
   public static DataSource getDataSource() {return  dataSourceInstance; }

    private BackendFactory() {
    }



}
