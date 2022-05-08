package com.sibaamap.forgroundandbound;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnStartService,btnStopForegroundService,btnStopBoundService;
    private MyService myService;
    private boolean isServiceConnected;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            MyService.MyBinder myBinder = (MyService.MyBinder) service;
            myService = myBinder.getMyService();
            isServiceConnected = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            myService = null;
            isServiceConnected = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStartService = findViewById(R.id.btn_start_service);
        btnStopBoundService = findViewById(R.id.btn_stop_bound_service);
        btnStopForegroundService = findViewById(R.id.btn_stop_service);


        btnStartService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickStartService();
            }
        });
        btnStopForegroundService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickStopForegroundService();
            }
        });
        btnStopBoundService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickStopBoundService();
            }
        });

    }

    private void onClickStopBoundService() {
        if(isServiceConnected){
            unbindService(serviceConnection);
        }
        isServiceConnected = false;
    }

    private void onClickStopForegroundService() {
        Intent intent = new Intent(this,MyService.class);
        stopService(intent);
    }

    private void onClickStartService() {
        Intent intent = new Intent(this,MyService.class);
        startService(intent);

        bindService(intent,serviceConnection, Context.BIND_AUTO_CREATE);


    }
}