package com.huatec.hiot_cloud.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.huatec.hiot_cloud.R;
import com.huatec.hiot_cloud.data.SharePreferencesHelper;
import com.huatec.hiot_cloud.ui.base.BaseActivity;
import com.huatec.hiot_cloud.ui.base.BasePresenter;
import com.huatec.hiot_cloud.ui.login.LoginActivity;
import com.huatec.hiot_cloud.ui.main.MainActivity;

import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;

public class SplashActivity extends BaseActivity {

    private static final int HANDLER_MSG_OPEN_NEW = 1;

    @Inject
    SharePreferencesHelper sharePreferencesHelper;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            Intent intent = null;
            if (msg.what == HANDLER_MSG_OPEN_NEW) {
                if (!TextUtils.isEmpty(sharePreferencesHelper.getUserToken())) {
                    intent = new Intent(SplashActivity.this, MainActivity.class);
                } else {
                    intent = new Intent(SplashActivity.this, LoginActivity.class);
                }
            }
            startActivity(intent);
            finish();
        }
    };

    @Override
    public BasePresenter createPresent() {
        return null;
    }

    @Override
    public void injectIndependies() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(HANDLER_MSG_OPEN_NEW);
            }
        },3000);

    }
}
