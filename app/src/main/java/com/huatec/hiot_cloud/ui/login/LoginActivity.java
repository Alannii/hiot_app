package com.huatec.hiot_cloud.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.huatec.hiot_cloud.R;
import com.huatec.hiot_cloud.ui.base.BaseActivity;
import com.huatec.hiot_cloud.ui.main.MainActivity;
import com.huatec.hiot_cloud.utils.LoadingUtil;
import com.huatec.hiot_cloud.utils.ValidatorUtils;

import javax.inject.Inject;


public class LoginActivity extends BaseActivity<LoginView,LoginPresenter> implements LoginView{

    TextInputEditText tiptetEmail;
    TextInputEditText tiptetPassword;

    @Inject
    LoginPresenter loginPresenter;


    @Override
    public LoginPresenter createPresent() {
        return loginPresenter;
    }

    @Override
    public void injectIndependies() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        tiptetEmail = findViewById(R.id.tiptet_email);
        tiptetPassword = findViewById(R.id.tiptet_password);

        Button btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = tiptetEmail.getText().toString();
                String password = tiptetPassword.getText().toString();
                if (ValidateSucc(email,password)){
                    //请求服务端身份验证
                    //如果校验成功，则保存登录状态，跳转到列表界面
                    LoadingUtil.showLoading(LoginActivity.this, "正在登录。。。");
                    loginPresenter.login(email,password);

                }
            }
        });
    }

    /**
     * 校验用户输入
     * @return
     */
    private boolean ValidateSucc(String email,String password) {



        //校验用户名非空
        if (TextUtils.isEmpty(email)){
            tiptetEmail.setError("用户名不能为空，请重新输入");
            return false;
        }
        //校验用户名合规
        if (!ValidatorUtils.isUserName(email)){
            tiptetEmail.setError("用户名输入不正确，请重新输入");
            return false;
        }

        //校验密码非空
        if (TextUtils.isEmpty(password)){
            tiptetPassword.setError("密码不能为空，请重新输入");
            return false;
        }

        //校验密码合规
        if (!ValidatorUtils.isPassword(password)){
            tiptetPassword.setError("密码输入不正确，请重新输入");
            return false;
        }

        //校验用户名密码正确


        return true;
    }

    @Override
    public void loginSucc() {
        //跳转到主界面
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
