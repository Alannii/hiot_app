package com.huatec.hiot_cloud.test.glidetest;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.huatec.hiot_cloud.R;
import com.huatec.hiot_cloud.utils.ImageUtils;

public class TestGlideActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_glide);

        final ImageView iv = findViewById(R.id.iv_glide_test);

        //glide加载按钮
        Button btnGlideLoad = findViewById(R.id.btn_glide_load);
        btnGlideLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Glide.with(TestGlideActivity.this)
                        .load("http://p1.pstatp.com/large/166200019850062839d3")
                        .placeholder(R.drawable.loading)
                        .error(R.drawable.error)
                        .transition(new DrawableTransitionOptions().crossFade(3000))
                        .centerCrop()
                        .into(iv);
            }
        });

        Button btnGlideUtils = findViewById(R.id.btn_glide_util_load);
        btnGlideUtils.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageUtils.show(TestGlideActivity.this, iv, "http://p1.pstatp.com/large/166200019850062839d3");
            }
        });
    }
}
