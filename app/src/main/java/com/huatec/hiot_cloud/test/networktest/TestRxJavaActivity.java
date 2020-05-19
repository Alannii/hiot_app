package com.huatec.hiot_cloud.test.networktest;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.huatec.hiot_cloud.R;
import com.huatec.hiot_cloud.data.NetworkService;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class TestRxJavaActivity extends AppCompatActivity {

    private static final String TAG = "TestRxJavaActivity";

    private Retrofit retrofit;

    private NetworkService service;

    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_rx_java);

        editText = findViewById(R.id.et_token_rxjava);

        createRetrofit();

        //登录
        Button  btnLogin = findViewById(R.id.btn_rxjava_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login("YuBingFeng","abc123");
            }
        });

        //用户信息
        Button btnUserInfo = findViewById(R.id.btn_rxjava_userinfo);
        btnUserInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getUserInfo("c6ffd6a17f4042b1ae29262be22462dd_5789b71767614791afef6c1cdab5f265_use");
            }
        });

        //修改邮箱
        Button btnUpdateEmail = findViewById(R.id.btn_rxjava_update_email);
        btnUpdateEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateEmail("7777777887@qq.com","c6ffd6a17f4042b1ae29262be22462dd_5789b71767614791afef6c1cdab5f265_use");
            }
        });

        //注册
        Button btnRegister = findViewById(R.id.btn_rxjava_register);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserBean userBean = new UserBean();
                userBean.setEmail("12s32156@qq.com");
                userBean.setUsername("YBF2");
                userBean.setPassword("abc123");
                userBean.setUserType("app");
                register(userBean);
            }
        });
    }

    /**
     * 登录
     * @param UserName
     * @param Passwd
     */
    private void login(String UserName, String Passwd) {
        service.login(UserName,Passwd,"app")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResultBase<LoginResultDTO>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        
                    }

                    @Override
                    public void onNext(ResultBase<LoginResultDTO> loginResultDTOResultBase) {
                        if (loginResultDTOResultBase != null && loginResultDTOResultBase.getData() != null){
                            LoginResultDTO loginResultDTO = loginResultDTOResultBase.getData();
                            Log.d(TAG, "onNext: "+loginResultDTO.getTokenValue());
                            editText.setText(loginResultDTO.getTokenValue());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: "+e.getMessage(),e);

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 获取用户信息
     * @param
     */
    private void getUserInfo(String authorization) {
        Observable<ResultBase<UserBean>> observable = service.getUserInfo(authorization);
        observable.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Observer<ResultBase<UserBean>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ResultBase<UserBean> userBeanResultBase) {
                if(userBeanResultBase != null || userBeanResultBase.getData() != null) {
                    String str = String.format("用户名：%s，邮箱：%s，密码：%s，用户类型：%s"
                            , userBeanResultBase.getData().getUsername(), userBeanResultBase.getData().getEmail(),userBeanResultBase.getData().getPassword(), userBeanResultBase.getData().getUserType());
                    Toast.makeText(TestRxJavaActivity.this, str, Toast.LENGTH_SHORT).show();
                }else if (userBeanResultBase != null && !TextUtils.isEmpty(userBeanResultBase.getMsg())){
                    Toast.makeText(TestRxJavaActivity.this,userBeanResultBase.getMsg() , Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    /**
     * 修改邮箱
     * @param
     * @param
     */
    private void updateEmail(String email, String authorization) {
        Observable<ResultBase<String>> observable = service.updateEmail(email,authorization);
        observable.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Observer<ResultBase<String>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ResultBase<String> stringResultBase) {
                if(stringResultBase != null && stringResultBase.getData() != null) {
                    Toast.makeText(TestRxJavaActivity.this, "修改后的邮箱"+stringResultBase.getData(), Toast.LENGTH_SHORT).show();
                }else if (stringResultBase != null && !TextUtils.isEmpty(stringResultBase.getMsg())){
                    Toast.makeText(TestRxJavaActivity.this,stringResultBase.getMsg() , Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    /**
     * 注册
     */
    private void register(UserBean userBean) {
        Observable<ResultBase<UserBean>> observable = service.register(userBean);
        observable.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Observer<ResultBase<UserBean>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ResultBase<UserBean> userBeanResultBase) {
                Toast.makeText(TestRxJavaActivity.this,userBeanResultBase.getMsg() , Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    /**
         * 创建retrofit方法
         */
        private void createRetrofit(){
            retrofit = new Retrofit.Builder().baseUrl(NetworkService.baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
            service = retrofit.create(NetworkService.class);

    }
}
