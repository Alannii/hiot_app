package com.huatec.hiot_cloud.test.mvptest;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.huatec.hiot_cloud.R;
import com.huatec.hiot_cloud.base.BaseActivity;
import com.huatec.hiot_cloud.test.dagger2test.ThirdObj;
import com.huatec.hiot_cloud.test.User;

import javax.inject.Inject;

public class TestMVPActivity extends BaseActivity<TestView,TestPresenter> implements TestView{

    @Inject
    ThirdObj thirdObj;


    @Inject
    TestPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_test_m_v_p);
        final EditText username = findViewById(R.id.et_user_name_2);
        final EditText passwd = findViewById(R.id.et_passwd_2);
        Button button = findViewById(R.id.bt_login_2);

        final User user = new User();

        presenter.setView(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setUserName(username.getText().toString());
                user.setPasswd(passwd.getText().toString());
                thirdObj.thirdAction();
                presenter.login(user);
            }
        });
    }

    @Override
    public TestPresenter createPresent() {
        return presenter;
    }

    @Override
    public void injectIndependies() {
        getActivityComponent().inject(this);
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}
