package com.huatec.hiot_cloud.ui.register;

import android.text.TextUtils;

import com.huatec.hiot_cloud.data.DataManager;
import com.huatec.hiot_cloud.test.networktest.LoginResultDTO;
import com.huatec.hiot_cloud.test.networktest.ResultBase;
import com.huatec.hiot_cloud.test.networktest.UserBean;
import com.huatec.hiot_cloud.ui.base.BasePresenter;
import com.huatec.hiot_cloud.utils.Constants;

import javax.inject.Inject;

public class RegisterPresenter extends BasePresenter<RegisterView> {

    @Inject
    DataManager dataManager;

    @Inject
    public RegisterPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }


    public void register(String username, String email, String passwd) {
        subscrib(dataManager.register(username, passwd, email), new RequestCallback<ResultBase<UserBean>>() {
            @Override
            public void onNext(ResultBase<UserBean> userBeanResultBase) {
                if (userBeanResultBase.getStatus() == Constants.MSG_STATUS_SUCCESS) {
                    if (userBeanResultBase != null && userBeanResultBase.getData() != null) {
                        //判断如果注册成功，吐司注册成功
                        getView().showMessage("注册成功");

                        //自动登录
                        getView().registerSucc(username, passwd);
                    }

                } else {
                    //判断如果注册失败，吐司服务端返回的错误信息
                    if (userBeanResultBase != null && !TextUtils.isEmpty(userBeanResultBase.getMsg())) {
                        getView().showMessage(userBeanResultBase.getMsg());
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
                if (loginResultDTOResultBase.getStatus() == Constants.MSG_STATUS_SUCCESS) {
                    //如果登录身份正确，跳转到主界面
                    if (loginResultDTOResultBase != null && loginResultDTOResultBase.getData() != null) {
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
