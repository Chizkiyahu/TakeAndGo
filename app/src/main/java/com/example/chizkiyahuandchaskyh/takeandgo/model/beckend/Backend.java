package com.example.chizkiyahuandchaskyh.takeandgo.model.beckend;

/**
 * Created by chezkiaho on 19.3.2018.
 */

public interface Backend {

    boolean logon();
    boolean checkUserPass (String username, String Password);
    boolean checkUserFree(String username);
    void addUserPass(String username, String Password) throws Exception;
}
