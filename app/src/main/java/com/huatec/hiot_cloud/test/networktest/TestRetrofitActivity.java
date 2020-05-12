package com.huatec.hiot_cloud.test.networktest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huatec.hiot_cloud.R;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TestRetrofitActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private TestRetrofitService service;
    private Gson gson = new Gson();
    private static final String TAG = "TestRetrofitActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_retrofit);

        //创建retrofit对象
        createRetrofit();

        //百度
        Button btnTest = findViewById(R.id.btn_retrofit_test);
        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test();
            }
        });

        //登录
        Button btnLogin = findViewById(R.id.btn_retrofit_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login("YuBingFeng","abc123","app");
            }
        });


        //用户信息
        Button btnGetUserInfo = findViewById(R.id.btn_retrofit_userinfo);
        btnGetUserInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getUserInfo3("c6ffd6a17f4042b1ae29262be22462dd_cba5f132ef904ae68618b8b892bcb716_use");
            }
        });

        //修改邮箱
        Button btnUpdateEmail = findViewById(R.id.btn_retrofit_update_email);
        btnUpdateEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateEmail("123411126@qq.com","c6ffd6a17f4042b1ae29262be22462dd_4903152737d047aa86655c495961ab78_use");
            }
        });

        //注册
        Button btnRegister = findViewById(R.id.btn_retrofit_register);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserBean userBean = new UserBean();
                userBean.setEmail("12s34356@qq.com");
                userBean.setUsername("YBF");
                userBean.setPassword("abc123");
                userBean.setUserType("app");
                register(userBean);
            }
        });
    }

    /**
     * 百度
     */
    private void test() {
        Call<ResponseBody> call = service.test();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.d(TAG, "onResponse: "+response.body().string());
                } catch (IOException e) {
                    Log.e(TAG, "onResponse: "+e.getMessage(),e);
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
            }
        });

    }

    /**
     * 登录方式1
     * @param username
     * @param passwd
     * @param logincod
     */
    private void login(String username, String passwd, String logincod) {
        Call<ResponseBody> call = service.login(username,passwd,logincod);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Type type = new TypeToken<ResultBase<LoginResultDTO>>(){}.getType();
                    ResultBase<LoginResultDTO> resultBase =  gson.fromJson(response.body().string(),type);
                    if(resultBase != null || resultBase.getData() != null) {
                        EditText editText = findViewById(R.id.et_token_retrofit);
                        editText.setText(resultBase.data.getTokenValue());
                    }

                } catch (IOException e) {
                    Log.e(TAG, "onResponse: "+e.getMessage(),e);
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
            }
        });
    }

    /**
     * 登录方式2
     * @param username
     * @param passwd
     * @param logincod
     */
    private void login2(String username, String passwd, String logincod) {
        Call<ResponseBody> call = service.login2(username,passwd,logincod);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.d(TAG, "onResponse: "+response.body().string());
                } catch (IOException e) {
                    Log.e(TAG, "onResponse: "+e.getMessage(),e);
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
            }
        });
    }

    /**
     * 用户信息查询
     * @param "token值"
     */
    private void getUserInfo(String authorization) {
        Call<ResponseBody> call = service.getUserInfo(authorization);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.d(TAG, "onResponse: "+response.body().string());
                } catch (IOException e) {
                    Log.e(TAG, "onResponse: "+e.getMessage(),e);
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
            }
        });
    }

    /**
     * 用户信息查询2
     * @param "token值"
     */
    private void getUserInfo2(String authorization) {
        Call<ResponseBody> call = service.getUserInfo2(authorization);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Type type = new TypeToken<ResultBase<UserBean>>(){}.getType();
                    ResultBase<UserBean> resultBase = gson.fromJson(response.body().string(),type);
                    if(resultBase != null || resultBase.getData() != null) {
                        String str = String.format("用户名：%s，邮箱：%s，密码：%s，用户类型：%s"
                                , resultBase.data.getUsername(), resultBase.data.getEmail(), resultBase.data.getPassword(), resultBase.data.getUserType());
                        Toast.makeText(TestRetrofitActivity.this, str, Toast.LENGTH_SHORT).show();
                    }

                    if (resultBase != null && resultBase.getMsg() != null){
                        Toast.makeText(TestRetrofitActivity.this, resultBase.getMsg(), Toast.LENGTH_SHORT).show();
                    }

                } catch (IOException e) {
                    Log.e(TAG, "onResponse: "+e.getMessage(),e);
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
            }
        });
    }

    /**
     * 用户信息查询3
     * @param "token值"
     */
    private void getUserInfo3(String authorization) {
        Call<ResultBase<UserBean>> call = service.getUserInfo3(authorization);
        call.enqueue(new Callback<ResultBase<UserBean>>() {
            @Override
            public void onResponse(Call<ResultBase<UserBean>> call, Response<ResultBase<UserBean>> response) {
                ResultBase<UserBean> resultBase = response.body();
                if(resultBase != null && resultBase.getData() !=null){
                String str = String.format("用户：%s，邮箱：%s",resultBase.getData().getUsername(),resultBase.getData().getEmail());
                    Toast.makeText(TestRetrofitActivity.this, str, Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ResultBase<UserBean>> call, Throwable t) {
            }
        });
    }

    /**
     * 修改邮箱
     * @param email
     * @param authorication
     */
    private void updateEmail(String email, String authorication) {
        Call<ResponseBody> call = service.updateEmail(email,authorication);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.d(TAG, "onResponse: "+response.body().string());
                } catch (IOException e) {
                    Log.e(TAG, "onResponse: "+e.getMessage(),e);
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
            }
        });
    }


    /**
     * 注册
     */
    private void register(UserBean userBean) {
        Call<ResponseBody> call = service.register(userBean);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.d(TAG, "onResponse: "+response.body().string());
                } catch (IOException e) {
                    Log.e(TAG, "onResponse: "+e.getMessage(),e);
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
            }
        });
    }

    private void createRetrofit(){
        retrofit = new Retrofit.Builder().baseUrl(TestRetrofitService.baseUrl)
                .addConverterFactory(GsonConverterFactory.create()).build();
        service = retrofit.create(TestRetrofitService.class);
    }
}
