package com.example.chizkiyahuandchaskyh.takeandgo.model.beckend;

class BackendFactory {
    private static final BackendFactory ourInstance = new BackendFactory();

    static BackendFactory getInstance() {
        return ourInstance;
    }

    private BackendFactory() {
    }
}
