package com.huatec.hiot_cloud.test.networktest;

import android.text.TextUtils;

import com.huatec.hiot_cloud.data.DataManager;
import com.huatec.hiot_cloud.ui.base.BasePresenter;
import com.huatec.hiot_cloud.ui.devicelist.bean.UserBean;

import javax.inject.Inject;


public class TestNetworkPresenter extends BasePresenter<TestNetworkView> {


    @Inject
    DataManager dataManager;

    @Inject
    public TestNetworkPresenter() {
    }

    public void login(String userName, String password) {
        subscrib(dataManager.login(userName, password), new RequestCallback<ResultBase<LoginResultDTO>>() {
            @Override
            public void onNext(ResultBase<LoginResultDTO> loginResultDTOResultBase) {
                if (loginResultDTOResultBase != null && loginResultDTOResultBase.getData() != null) {
                    getView().showToken(loginResultDTOResultBase.getData().getTokenValue());

                } else if (loginResultDTOResultBase != null && !TextUtils.isEmpty(loginResultDTOResultBase.getMsg())) {
                    getView().showMessage(loginResultDTOResultBase.getMsg());
                }
            }
        });

    }

    /**
     * 获取用户信息
     *
     * @param
     */
    public void getUserInfo(String authorization) {
        subscrib(dataManager.getUserInfo(), new RequestCallback<ResultBase<UserBean>>() {

            @Override
            public void onNext(ResultBase<UserBean> userBeanResultBase) {
                if (userBeanResultBase != null || userBeanResultBase.getData() != null) {
                    String str = String.format("用户名：%s，邮箱：%s，密码：%s，用户类型：%s"
                            , userBeanResultBase.getData().getUsername(), userBeanResultBase.getData().getEmail(), userBeanResultBase.getData().getPassword(), userBeanResultBase.getData().getUserType());
                    getView().showMessage(str);
                } else if (userBeanResultBase != null && !TextUtils.isEmpty(userBeanResultBase.getMsg())) {
                    getView().showMessage(userBeanResultBase.getMsg());
                }
            }
        });
    }

    /**
     * 修改邮箱
     *
     * @param
     * @param
     */
    public void updateEmail(String email, String authorization) {
        subscrib(dataManager.updateEmail(email), new RequestCallback<ResultBase<String>>() {
            @Override
            public void onNext(ResultBase<String> stringResultBase) {
                if (stringResultBase != null && stringResultBase.getData() != null) {
                    getView().showMessage("修改后的邮箱" + stringResultBase.getData());
                } else if (stringResultBase != null && !TextUtils.isEmpty(stringResultBase.getMsg())) {
                    getView().showMessage(stringResultBase.getMsg());
                }
            }
        });
    }


    /**
     * 注册
     */
    public void register(String userName, String password, String email) {
        subscrib(dataManager.register(userName, password, email), new RequestCallback<ResultBase<UserBean>>() {
            @Override
            public void onNext(ResultBase<UserBean> userBeanResultBase) {
                getView().showMessage(userBeanResultBase.getMsg());
            }
        });
    }
}
