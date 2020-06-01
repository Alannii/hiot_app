package com.huatec.hiot_cloud.ui.updatepasswd;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.huatec.hiot_cloud.R;
import com.huatec.hiot_cloud.ui.base.BaseActivity;
import com.huatec.hiot_cloud.ui.main.MainActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UpdatePasswdActivity extends BaseActivity<UpdatePasswordView, UpdatePasswordPresenter> implements UpdatePasswordView {

    @Inject
    UpdatePasswordPresenter presenter;
    @BindView(R.id.tv_old_passwd)
    EditText tvOldPasswd;
    @BindView(R.id.tv_new_passswd)
    EditText tvNewPassswd;
    @BindView(R.id.tv_update_passwd)
    EditText tvUpdatePasswd;
    @BindView(R.id.btn_update_passwd)
    Button btnUpdatePasswd;

    @Override
    public UpdatePasswordPresenter createPresent() {
        return presenter;
    }

    @Override
    public void injectIndependies() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_passwd);
        ButterKnife.bind(this);
    }

    @Override
    public void updateSucc() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_update_passwd)
    public void onViewClicked() {
        String oldpassword = tvOldPasswd.getText().toString();
        String newpassword = tvNewPassswd.getText().toString();
        String confirmpassword = tvUpdatePasswd.getText().toString();
        presenter.updatePassword(oldpassword, newpassword, confirmpassword);
    }
}