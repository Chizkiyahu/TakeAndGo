package com.example.chizkiyahuandchaskyh.takeandgo.model.beckend;

import com.example.chizkiyahuandchaskyh.takeandgo.model.datasource.DatabaseList;

public class BackendFactory {
    private static final BackendFactory ourInstance = new BackendFactory();

    static BackendFactory getInstance() {
        return ourInstance;
    }

    static DataSource dataSourceInstance = new DatabaseList();
   public static DataSource getDataSource() {return  dataSourceInstance; }

    private BackendFactory() {
    }


    public static BackendFactory getOurInstance() {
        return ourInstance;
    }
}
