package com.huatec.hiot_cloud.ui.updateemail;

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

public class UpdateEmailActivity extends BaseActivity<UpdateEmailView, UpdateEmailPresenter> implements UpdateEmailView {

    @BindView(R.id.tv_update_email)
    EditText tvUpdateEmail;
    @BindView(R.id.btn_update_email)
    Button btnUpdateEmail;

    @Inject
    UpdateEmailPresenter presenter;

    @Override
    public UpdateEmailPresenter createPresent() {
        return presenter;
    }

    @Override
    public void injectIndependies() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_email);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_update_email)
    public void onViewClicked() {
        String email = tvUpdateEmail.getText().toString();
        presenter.updateEmail(email);
    }

    @Override
    public void updateSucc() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}