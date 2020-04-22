package com.huatec.hiot_cloud.test.mvctest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.huatec.hiot_cloud.R;
import com.huatec.hiot_cloud.test.User;

public class TestMVCActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_m_v_c);
        Log.d("tag", "test");
        final User user = new User();

        final EditText username = findViewById(R.id.et_user_name);
        final EditText passwd = findViewById(R.id.et_passwd);
        Button button = findViewById(R.id.bt_login);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //MVC做法，在这里做身份校验
                user.setUserName(username.getText().toString());
                user.setPasswd(passwd.getText().toString());
                login(user);
            }
        });
    }

    private void login(User user) {
        if ("lisi".equals(user.getUserName()) && "123".equals(user.getPasswd())) {
            Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "登录失败", Toast.LENGTH_SHORT).show();
        }
    }
}
