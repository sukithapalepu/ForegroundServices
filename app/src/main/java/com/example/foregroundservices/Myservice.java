package com.example.foregroundservices;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class Myservice extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @TargetApi( Build.VERSION_CODES.O)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Notification Service started by user.", Toast.LENGTH_LONG).show();
        String NOTIFICATION_CHANNEL_ID = "com.example.foregroundservices";
        String channelName = "My Background Service";
        //  NotificationChannel channel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, channelName, NotificationManager.IMPORTANCE_NONE);

        // channel.setLightColor(Color.BLUE);

        //  channel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        assert manager != null;
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
        //  Notification notification = new Notification.Builder(this, NOTIFICATION_CHANNEL_ID)
        //  .setSmallIcon(R.mipmap.ic_launcher)
        //  .setContentTitle("My Awesome App")
        // .setContentIntent(pendingIntent).build();
        // startForeground(1337, notification);

        return START_STICKY;
    }
@RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onDestroy() {
            super.onDestroy();

        stopForeground(true);
        Toast.makeText(this, "Notification Service destroyed by user.", Toast.LENGTH_LONG).show();
    }
}
