package com.sibaamap.forgroundandbound;

import static com.sibaamap.forgroundandbound.MyApplication.CHANNEL_ID;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class MyService extends Service {

    private MyBinder myBinder = new MyBinder();

    public class MyBinder extends Binder {
        MyService getMyService(){
            return MyService.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e("Tincoder","MyService onBind");

        return myBinder;
    }

    @Override
    public void onCreate() {
        Log.e("Tincoder","MyService onCreate");
        super.onCreate();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e("Tincoder","MyService onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.e("Tincoder","MyService onDestroy");
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("Tincoder","MyService onStartCommand");
        sendNotification();
        return START_NOT_STICKY;
    }

    private void sendNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,CHANNEL_ID);
        builder.setContentTitle("Chi con mot dem cuoi");
        builder.setContentText("tuan hung");
        builder.setSmallIcon(R.drawable.ic_small_notification);

        Notification notification = builder.build();

        startForeground(1,notification);

    }
}
