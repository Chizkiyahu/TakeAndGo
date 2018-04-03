package com.example.chizkiyahuandchaskyh.takeandgo.model.beckend;

public interface LoginData {


    boolean TryUserPass(String username, String Password) throws Exception;
    boolean checkUserIsFree(String username) throws Exception;
    void addUserPass(String username, String Password) throws Exception;
}
