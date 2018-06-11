package com.example.chizkiyahuandchaskyh.takeandgo.controller;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.chizkiyahuandchaskyh.takeandgo.R;
import com.example.chizkiyahuandchaskyh.takeandgo.model.beckend.BackendFactory;
import com.example.chizkiyahuandchaskyh.takeandgo.model.datasource.DatabaseList;
import com.example.chizkiyahuandchaskyh.takeandgo.model.datasource.DatabaseSQL;
import com.example.chizkiyahuandchaskyh.takeandgo.model.utils.Constants;
import com.example.chizkiyahuandchaskyh.takeandgo.service.findFreeCarService;

import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkLogin();
        new AsyncTask<Void, Void, Boolean>(){
            @Override
            protected Boolean doInBackground(Void... voids) {
                Boolean internetWork = false;
                try {
                    internetWork = isConnectedToInternet();
                    if (internetWork) {
                        startService(new Intent(getBaseContext(), findFreeCarService.class));
                    }
                }catch (Exception e){
                    Log.e(Constants.Log.TAG,e.getMessage());
                }
                return internetWork;
            }

            @Override
            protected void onPostExecute(Boolean aBoolean) {
                if(!aBoolean){
                    BackendFactory.setInstance(new DatabaseList());
                    AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                    alertDialog.setTitle("Error");
                    alertDialog.setMessage("Error: No Internet access The Database is from List");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }


            }
        }.execute();



    }

    void checkLogin(){

        SharedPreferences prefs = getSharedPreferences("UserData", MODE_PRIVATE);
        if(! prefs.getBoolean("isLogon", false)){
            startActivity(new Intent( MainActivity.this, LoginActivity.class));

        }
    }


    public void onClickBranchs(View view) {
        startActivity(new Intent(MainActivity.this, BranchesActivity.class));
    }

    public void onClickCustomers(View view) {
        startActivity(new Intent(MainActivity.this, CustomersActivity.class));
    }

    public void onClickModels(View view) {
        startActivity(new Intent(MainActivity.this, ModelsActivity.class));
    }

    public void onClickCars(View view) {
        startActivity(new Intent(MainActivity.this, CarsActivity.class));
    }




    @Override
    public void onBackPressed() {
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    public void onClickLogOut(MenuItem item) {
        SharedPreferences prefs = getSharedPreferences("UserData", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("isLogon", false);
        editor.putInt("failedLogin", 0);
        editor.commit();
        checkLogin();
    }

    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager)     getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo().isConnectedOrConnecting();

    }
    public boolean isConnectedToInternet()
    {
        try{
            ConnectivityManager cm = (ConnectivityManager) getSystemService
                    (Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();



            if (netInfo != null && netInfo.isConnected())
            {
                //Network is available but check if we can get access from the network.
                //URL url = new URL("https://www.google.com/");
                URL url = new URL("http://rafol.vlab.jct.ac.il/TakeAndGo/checkInt.php");
                HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
                urlc.setRequestProperty("Connection", "close");
                urlc.setConnectTimeout(2000); // Timeout 2 seconds.

                urlc.connect();


                if (urlc.getResponseCode() == 200)  //Successful response.
                {
                    return true;
                }
                else
                {
                    Log.d("NO INTERNET", "NO INTERNET");
                    return false;
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }
}
