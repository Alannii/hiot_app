package com.huatec.hiot_cloud.data;

import com.huatec.hiot_cloud.test.networktest.LoginResultDTO;
import com.huatec.hiot_cloud.test.networktest.ResultBase;
import com.huatec.hiot_cloud.test.networktest.UserBean;
import com.huatec.hiot_cloud.utils.Constants;

import javax.inject.Inject;

import io.reactivex.Observable;


/**
 * 网络请求封装类
 */
public class DataManager {

    private NetworkService service;

    @Inject
    public DataManager(NetworkService service) {
        this.service = service;
    }

    /**
     * 登录
     *
     * @param userName
     * @param password
     * @return
     */
    public Observable<ResultBase<LoginResultDTO>> login(String userName, String password) {
        return service.login(userName, password, Constants.LOGIN_CODE_APP);
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
