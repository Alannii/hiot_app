package com.huatec.hiot_cloud.ui.login;

import android.text.TextUtils;

import com.huatec.hiot_cloud.data.DataManager;
import com.huatec.hiot_cloud.test.networktest.LoginResultDTO;
import com.huatec.hiot_cloud.test.networktest.ResultBase;
import com.huatec.hiot_cloud.ui.base.BasePresenter;

import javax.inject.Inject;

class LoginPresenter extends BasePresenter<LoginView> {

    @Inject
    DataManager dataManager;

    @Inject
    public LoginPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    /**
     * 登录
     *
     * @param email
     * @param password
     */
    public void login(String email, String password) {
        subscrib(dataManager.login(email, password), new RequestCallback<ResultBase<LoginResultDTO>>() {
            @Override
            public void onNext(ResultBase<LoginResultDTO> loginResultDTOResultBase) {
                if (loginResultDTOResultBase.getStatus() == 1) {
                    //如果登录身份正确，跳转到主界面
                    if (loginResultDTOResultBase != null && loginResultDTOResultBase.getData() != null) {
                        //弹出登录成功
                        getView().showMessage("登录成功");

                        //跳转到主界面
                        getView().loginSucc();
                    }
                } else {
                    if (loginResultDTOResultBase != null && !TextUtils.isEmpty(loginResultDTOResultBase.getMsg())) {
                        getView().showMessage("登录失败");
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                getView().showMessage("当前网络无法访问，请稍后再试");
            }
        });
    }
}