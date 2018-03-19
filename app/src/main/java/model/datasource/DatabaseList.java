package model.datasource;

import java.util.ArrayList;

import model.beckend.Backend;

/**
 * Created by chezkiaho on 19.3.2018.
 */


public class DatabaseList implements Backend {


    private class Users {

        String username;
        String password;

        public Users(String user, String pass){
            this.username = user;
            this.password = pass;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public String getUsername() {
            return username;
        }
    }

    ArrayList<Users> users = new ArrayList<>();

    @Override
    public boolean checkUserPass(String username, String password) {

        for (Users userPass:users) {
            if (userPass.getUsername().equals(username)){
                if (userPass.getPassword().equals(password)) {
                    return true;
                }
                else {
                    return  false;
                }
            }
        }
    return false;
    }

    @Override
    public boolean checkUserFree(String username) {
        for (Users userPass:users) {
            if (userPass.getUsername().equals(username)){
                return false;
            }
        }
        return true;
    }

    @Override
    public void addUserPass(String username, String password) throws Exception{
        if(checkUserFree(username)){
            users.add(new Users(username, password));
        }else {
            throw new Exception("the username alredy exsiste");
        }

    }
}
