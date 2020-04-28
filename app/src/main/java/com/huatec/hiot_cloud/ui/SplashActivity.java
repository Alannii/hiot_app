package com.huatec.hiot_cloud.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.huatec.hiot_cloud.R;
import com.huatec.hiot_cloud.ui.main.MainActivity;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;

public class SplashActivity extends AppCompatActivity {

    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            Intent intent = null;
            if (msg.what == 1){
                intent = new Intent(SplashActivity.this, MainActivity.class);
            }
            startActivity(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(1);
            }
        },3000);

    }


}
