package com.huatec.hiot_cloud.ui.register;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.huatec.hiot_cloud.R;
import com.huatec.hiot_cloud.ui.base.BaseActivity;
import com.huatec.hiot_cloud.ui.login.LoginActivity;
import com.huatec.hiot_cloud.ui.main.MainActivity;
import com.huatec.hiot_cloud.utils.ValidatorUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity<RegisterView, RegisterPresenter> implements RegisterView {

    @BindView(R.id.tiptet_username)
    TextInputEditText tiptetUsername;
    @BindView(R.id.tiptet_email)
    TextInputEditText tiptetEmail;
    @BindView(R.id.tiptet_password)
    TextInputEditText tiptetPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.tv_link_signup)
    TextView tvLinkSignup;

    @Inject
    RegisterPresenter registerPresenter;


    @Override
    public RegisterPresenter createPresent() {
        return registerPresenter;
    }

    @Override
    public void injectIndependies() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        ButterKnife.bind(this);

    }

    /**
     * 校验用户输入
     *
     * @return
     */
    private boolean ValidateSucc(String username, String email, String password) {
        //校验用户名非空
        if (TextUtils.isEmpty(username)) {
            tiptetUsername.setError("用户名不能为空，请重新输入");
            return false;
        }
        //校验用户名合规
        if (!ValidatorUtils.isUserName(username)) {
            tiptetUsername.setError("用户名输入不正确，请重新输入");
            return false;
        }

        //校验邮箱非空
        if (TextUtils.isEmpty(email)) {
            tiptetEmail.setError("邮箱不能为空，请重新输入");
            return false;
        }
        //校验邮箱合规
        if (!ValidatorUtils.isEmail(email)) {
            tiptetEmail.setError("邮箱输入不正确，请重新输入");
            return false;
        }

        //校验密码非空
        if (TextUtils.isEmpty(password)) {
            tiptetPassword.setError("密码不能为空，请重新输入");
            return false;
        }

        //校验密码合规
        if (!ValidatorUtils.isPassword(password)) {
            tiptetPassword.setError("密码输入不正确，请重新输入");
            return false;
        }
        return true;
    }

    @OnClick({R.id.btn_login, R.id.tv_link_signup})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.btn_login:
                //注册操作
                String username = tiptetUsername.getText().toString();
                String email = tiptetEmail.getText().toString();
                String passwd = tiptetPassword.getText().toString();
                if (ValidateSucc(username, email, passwd)) {
                    registerPresenter.register(username, email, passwd);
                }
                break;
            case R.id.tv_link_signup:
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void registerSucc(String username, String passwd) {
        //注册成功后做自动登录
        registerPresenter.login(username, passwd);
        loginSucc();
    }

    @Override
    public void loginSucc() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
