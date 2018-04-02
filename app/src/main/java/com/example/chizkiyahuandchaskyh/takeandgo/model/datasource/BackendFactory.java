package com.example.chizkiyahuandchaskyh.takeandgo.model.datasource;

import com.example.chizkiyahuandchaskyh.takeandgo.model.beckend.Backend;

/**
 * Created by chezkiaho on 19.3.2018.
 */

public  class BackendFactory {
    Backend instance = new DatabaseList();

    public Backend getInstance() {
        return instance;
    }

    public void setInstance(Backend instance) {
        this.instance = instance;
    }
}
