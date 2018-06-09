package com.example.chizkiyahuandchaskyh.takeandgo.service;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.example.chizkiyahuandchaskyh.takeandgo.model.beckend.BackendFactory;
import com.example.chizkiyahuandchaskyh.takeandgo.model.beckend.DataSource;
import com.example.chizkiyahuandchaskyh.takeandgo.model.entities.Car;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class findFreeCarService extends Service {

    private Timer timer = new Timer();
    static final int UPDATE_INTERVAL = 1000 * 10;
    private Map<Integer, Car> freeCarsCarMap = new HashMap<>();
    DataSource dataSource = BackendFactory.getDataSource();
    public findFreeCarService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        for (Car car : dataSource.getFreeCarList()){
            freeCarsCarMap.put(car.getId(), car);
        }
    }

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                Log.d("MyService", "Start chack");
                ArrayList<Car> cars = dataSource.getFreeCarList();
                for (Car car :  cars){
                    if (freeCarsCarMap.get(car.getId()) == null){
                        Log.d("MyService", "Start Sending message...");
                        Intent broadcastIntent = new Intent("NEW_CAR_IS_FREE");

                        sendBroadcast(broadcastIntent);
                        Log.d("MyService", "End Sending message...");
                        break;
                    }
                }
                freeCarsCarMap.clear();
                for (Car car : cars){
                    freeCarsCarMap.put(car.getId(), car);
                }
                Log.d("MyService", "End chack");
            }
        }, 1, UPDATE_INTERVAL);

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        timer.cancel();
        Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
    }
}
