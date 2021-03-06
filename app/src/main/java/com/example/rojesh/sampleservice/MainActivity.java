package com.example.rojesh.sampleservice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {
    private TextView mStart,mStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mStart = (TextView) findViewById(R.id.start);
        mStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCustomService();
            }
        });

        mStop = (TextView) findViewById(R.id.stop);
        mStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService();
            }
        });
    }

    //service onDestroy callback method will be called but sticky will wake up the service
    private void stopService() {
        Intent cstmServiec = new Intent(MainActivity.this, CustomService.class);
        stopService(cstmServiec);
    }


    private void startCustomService() {
        // use this to start and trigger a service
        Intent cstmServiec = new Intent(MainActivity.this, CustomService.class);
        // potentially add data to the intent
     //   cstmServiec.putExtra("KEY1", "Value to be used by the service");
        startService(cstmServiec);
    }
}
