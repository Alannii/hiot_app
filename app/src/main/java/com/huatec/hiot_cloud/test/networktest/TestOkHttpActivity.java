package com.huatec.hiot_cloud.test.networktest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.huatec.hiot_cloud.R;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.http.POST;

/**
 * OKHTTP测试类
 */
public class TestOkHttpActivity extends AppCompatActivity  {

    private static final String basUrl = "http://114.67.88.191:8080";
    private static final String TAG = "TestOkHttpActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_ok_http);

        //execute方法测试
        Button btnExecute = findViewById(R.id.btn_okhttp_execute);
        btnExecute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testExecute();
            }
        });

        //enqueue方法测试
        Button btnEnqueue = findViewById(R.id.btn_okhttp_enqueue);
        btnEnqueue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testEnqueue();
            }
        });

        //登录测试
        Button btnLogin = findViewById(R.id.btn_okhttp_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login("YuBingFeng","abc123","app");
            }
        });

        //用户信息测试
        Button btnGetUserInfo = findViewById(R.id.btn_okhttp_userinfo);
        btnGetUserInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getUserInfo("c6ffd6a17f4042b1ae29262be22462dd_795cfa8e10254b69a1187e4198e30edc_use");

            }
        });

        //修改邮箱测试
        Button btnUpdateEmail = findViewById(R.id.btn_okhttp_update_email);
        btnUpdateEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateEmail("5234114524@qq.com","c6ffd6a17f4042b1ae29262be22462dd_795cfa8e10254b69a1187e4198e30edc_use");
            }
        });
    }

    /**
     * 登录
     * @param "用户名"
     * @param "密码"
     * @param "登录方式"
     */
    private void login(String userName, String password, String loginCode) {

        OkHttpClient okHttpClient = new OkHttpClient();

        FormBody body =new FormBody.Builder().build();
        String url = basUrl+String.format("/auth/login?username=%s&password=%s&loginCode=%s",userName,password,loginCode);
        Request request = new Request.Builder().post(body).url(url).build();

        callEnqueue(okHttpClient, request);
    }

    /**
     * 查询用户信息
     * @param "Token值"
     */
    private void getUserInfo(String authorization) {

        OkHttpClient okHttpClient = new OkHttpClient();

        String url = basUrl+"/user/one";
        Request request = new Request.Builder().get().url(url)
                .header("Authorization",authorization).build();

        callEnqueue(okHttpClient, request);
    }

    private void callEnqueue(OkHttpClient okHttpClient, Request request) {
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.d(TAG, "onFailure: " + e.getMessage(), e);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                Log.d(TAG, "onResponse: " + response.body().string());
            }
        });
    }

    /**
     * 修改邮箱
     * @param "邮箱地址"
     * @param "Token值"
     */
    private void updateEmail(String email, String authorization) {
        OkHttpClient okHttpClient = new OkHttpClient();

        String url = basUrl+String.format("/user/email?email=%s",email);

        FormBody body =new FormBody.Builder().build();
        Request request = new Request.Builder().put(body).url(url)
                .header("Authorization",authorization).build();

        callEnqueue(okHttpClient, request);
    }

    /**
     * 测试execute方式
     */
    private void testExecute() {
        new Thread(new Runnable(){
            @Override
            public void run() {
                OkHttpClient okHttpClient = new OkHttpClient();
                Request request = new Request.Builder().url(basUrl).build();
                try {
                    Response response = okHttpClient.newCall(request).execute();
                    Log.d(TAG, "run:" + response.body().string());
                } catch (IOException e) {
                    Log.e(TAG, "testExecute: " + e.getMessage());
                }
            }
        }).start();
    }

    /**
     * 测试enqueue方式
     */
    private void testEnqueue() {

        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(basUrl).build();

        callEnqueue(okHttpClient, request);
    }

}
