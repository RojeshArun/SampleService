package com.example.rojesh.sampleservice;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Rojesh on 10-09-2016.
 */
public class CustomService extends Service {

    private static final String TAG = "HelloService";

    private boolean isRunning  = true;

    @Override
    public void onCreate() {
        Toast.makeText(getApplicationContext(), "On Create", Toast.LENGTH_SHORT).show();
        Log.e("Sample", "Create called");
        super.onCreate();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        Toast.makeText(getApplicationContext(), "On Start", Toast.LENGTH_SHORT).show();
        Log.e("Sample", "Start called");
        super.onStart(intent, startId);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(getApplicationContext(), "On Start Command", Toast.LENGTH_SHORT).show();
        Log.e("Sample", "On Start Command called");
        execueTask();

        //  Sticky will restart the service if killed
        return Service.START_STICKY;
    }

    private void execueTask() {
        //Creating new thread for my service
        //Always write your long running tasks in a separate thread, to avoid ANR
        new Thread(new Runnable() {
            @Override
            public void run() {


                //Your logic that service will perform will be placed here
                //In this example we are just looping and waits for 3000 milliseconds(3 Seconds) in each loop.
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(3000);
                    } catch (Exception e) {
                    }

                    if(isRunning){
                        Log.e(TAG, "Service running");

                        // Service we have created in a seperate thread so cannot interact with UI. So you need to communicate back to Main thead
                        // (Line 47 created new thread).
                        //If we kill the application also the service will be running in the background
                        Handler handler = new Handler(Looper.getMainLooper());
                        handler.post(new Runnable() {

                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(),
                                        "IsAlive",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }



                //Stop service once it finishes its task
               // stopSelf();
            }
        }).start();
    }

    @Override
    public IBinder onBind(Intent intent) {
        Toast.makeText(getApplicationContext(), "On Bind", Toast.LENGTH_SHORT).show();
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Toast.makeText(getApplicationContext(), "On UnBind", Toast.LENGTH_SHORT).show();
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Toast.makeText(getApplicationContext(), "On Destroy", Toast.LENGTH_SHORT).show();
        Log.e("Sample", "Destroy Called");
        super.onDestroy();
    }
}
