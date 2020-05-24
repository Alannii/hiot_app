package com.huatec.hiot_cloud.data;

import com.huatec.hiot_cloud.test.networktest.LoginResultDTO;
import com.huatec.hiot_cloud.test.networktest.ResultBase;
import com.huatec.hiot_cloud.test.networktest.UserBean;
import com.huatec.hiot_cloud.utils.Constants;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;


/**
 * 网络请求封装类
 */
public class DataManager {

    private NetworkService service;

    SharePreferencesHelper sharePreferencesHelper;

    @Inject
    public DataManager(NetworkService service, SharePreferencesHelper sharePreferencesHelper) {
        this.service = service;
        this.sharePreferencesHelper = sharePreferencesHelper;
    }

    /**
     * 登录
     *
     * @param userName
     * @param password
     * @return
     */
    public Observable<ResultBase<LoginResultDTO>> login(String userName, String password) {
        return service.login(userName, password, Constants.LOGIN_CODE_APP)
                .doOnNext(new Consumer<ResultBase<LoginResultDTO>>() {
                    @Override
                    public void accept(ResultBase<LoginResultDTO> loginResultDTOResultBase) throws Exception {
                        if (loginResultDTOResultBase.getStatus() == Constants.MSG_STATUS_SUCCESS) {
                            //如果登录身份正确，保存token值
                            sharePreferencesHelper.setUserToken(loginResultDTOResultBase.getData().getTokenValue());
                        }
                    }
                });
    }

    public Observable<ResultBase<UserBean>> getUserInfo(String authorization) {
        return service.getUserInfo(authorization);
    }

    public Observable<ResultBase<String>> updateEmail(String email, String authorization) {
        return service.updateEmail(email, authorization);
    }

    public Observable<ResultBase<UserBean>> register(String userName, String password, String email) {
        UserBean userBean = new UserBean();
        userBean.setUsername(userName);
        userBean.setPassword(password);
        userBean.setEmail(email);
        userBean.setUserType(Constants.REGISTER_TYPE_NORMAL);
        return service.register(userBean);

    }
}
