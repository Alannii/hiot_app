package com.huatec.hiot_cloud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.huatec.hiot_cloud.test.mvctest.TestMVCActivity;
import com.huatec.hiot_cloud.test.mvptest.TestMVPActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button mvc = findViewById(R.id.bt_mvc);
        Button mvp = findViewById(R.id.bt_mvp);

        mvc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity.this, TestMVCActivity.class);
                startActivity(intent);
            }
        });
        mvp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent =new Intent(MainActivity.this, TestMVPActivity.class);
                startActivity(intent);
            }
        });

    }
}