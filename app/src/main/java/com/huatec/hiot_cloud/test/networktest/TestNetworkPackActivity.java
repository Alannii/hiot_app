package com.huatec.hiot_cloud.test.networktest;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.huatec.hiot_cloud.R;
import com.huatec.hiot_cloud.data.DataManager;
import com.huatec.hiot_cloud.ui.base.BaseActivity;

import javax.inject.Inject;

public class TestNetworkPackActivity extends BaseActivity<TestNetworkView, TestNetworkPresenter> implements TestNetworkView {

    private static final String TAG = "TestNetworkPackActivity";

    @Inject
    DataManager dataManager;

    @Inject
    TestNetworkPresenter presenter;

    private EditText editText;

    @Override
    public TestNetworkPresenter createPresent() {
        return presenter;
    }

    @Override
    public void injectIndependies() {
        getActivityComponent().inject(this);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_network_pack);

        editText = findViewById(R.id.et_token_network_pack);


        //登录
        Button btnLogin = findViewById(R.id.btn_network_pack_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.login("YuBingFeng", "abc123");
            }
        });

        //用户信息
        Button btnUserInfo = findViewById(R.id.btn_network_pack_userinfo);
        btnUserInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.getUserInfo("c6ffd6a17f4042b1ae29262be22462dd_d734f0e12d0a44e09721622839edc578_use");
            }
        });

        //修改邮箱
        Button btnUpdateEmail = findViewById(R.id.btn_network_pack_update_email);
        btnUpdateEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.updateEmail("531h39j@ff.com", "c6ffd6a17f4042b1ae29262be22462dd_d734f0e12d0a44e09721622839edc578_use");
            }
        });

        //注册
        Button btnRegister = findViewById(R.id.btn_network_pack_register);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.register("yu123", "abc123", "931h329j@ff.com");
            }
        });
    }

    @Override
    public void showToken(String token) {
        editText.setText(token);
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(TestNetworkPackActivity.this, msg, Toast.LENGTH_SHORT).show();
    }
}

