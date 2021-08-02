package com.example.foregroundservices;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView text = findViewById(R.id.text);
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isMyServiceRunning(Myservice.class)) {
                    text.setText("Stopped");
                    stopService(new Intent(MainActivity.this, Myservice.class));
                }
                else {
                    text.setText("Started");
                    startService(new Intent(MainActivity.this, Myservice.class));
                }
            }
        });
    }
    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo Myservice : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(Myservice.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
}
