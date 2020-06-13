package com.huatec.hiot_cloud.data;

import com.huatec.hiot_cloud.test.networktest.LoginResultDTO;
import com.huatec.hiot_cloud.test.networktest.ResultBase;
import com.huatec.hiot_cloud.ui.devicelist.bean.DeviceBean;
import com.huatec.hiot_cloud.ui.devicelist.bean.UserBean;
import com.huatec.hiot_cloud.utils.Constants;

import java.io.File;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;


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

    /**
     * 获取用户信息
     *
     * @return
     */

    public Observable<ResultBase<UserBean>> getUserInfo() {
        return service.getUserInfo(sharePreferencesHelper.getUserToken());
    }

    /**
     * 修改邮箱
     *
     * @param email
     * @return
     */
    public Observable<ResultBase<String>> updateEmail(String email) {
        return service.updateEmail(email, sharePreferencesHelper.getUserToken());
    }

    public Observable<ResultBase<UserBean>> register(String userName, String password, String email) {
        UserBean userBean = new UserBean();
        userBean.setUsername(userName);
        userBean.setPassword(password);
        userBean.setEmail(email);
        userBean.setUserType(Constants.REGISTER_TYPE_NORMAL);
        return service.register(userBean);
    }

    /**
     * 修改密码
     *
     * @param oldpassword
     * @param newpassword
     * @param confirmpassword
     * @return
     */

    public Observable<ResultBase<String>> updatePassword(String oldpassword, String newpassword, String confirmpassword) {
        return service.updatePassword(oldpassword, newpassword, confirmpassword, sharePreferencesHelper.getUserToken());
    }

    /**
     * 上传头像
     *
     * @param filePath
     * @return
     */

    public Observable<ResultBase<String>> uploadImage(String filePath) {
        File file = new File(filePath);
        RequestBody requestBody = RequestBody.create(MediaType.parse(Constants.MULTIPART_FORM_DATA), file);
        MultipartBody.Part multipartFile = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
        return service.uploadImg(multipartFile, sharePreferencesHelper.getUserToken());
    }

    /**
     * 注销登录
     *
     * @return
     */
    public Observable<ResultBase> logout() {
        return service.logout(sharePreferencesHelper.getUserToken())
                .doOnNext(new Consumer<ResultBase>() {
                    @Override
                    public void accept(ResultBase resultBase) throws Exception {
                        sharePreferencesHelper.setUserToken("");
                    }
                });
    }

    /**
     * 绑定设备
     *
     * @return
     */
    public Observable<ResultBase> bindDevice(String deviceId) {
        return service.bindDevice(deviceId, sharePreferencesHelper.getUserToken());
    }

    /**
     * 查询绑定设备
     *
     * @return
     */
    public Observable<ResultBase<List<DeviceBean>>> listBindDevice(int bonding) {
        return service.listBindDevice(bonding, sharePreferencesHelper.getUserToken());
    }
}
