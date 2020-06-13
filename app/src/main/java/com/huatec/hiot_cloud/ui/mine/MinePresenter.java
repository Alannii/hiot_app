package com.huatec.hiot_cloud.ui.mine;

import com.huatec.hiot_cloud.data.DataManager;
import com.huatec.hiot_cloud.test.networktest.ResultBase;
import com.huatec.hiot_cloud.ui.base.BasePresenter;
import com.huatec.hiot_cloud.ui.devicelist.bean.UserBean;
import com.huatec.hiot_cloud.utils.Constants;

import javax.inject.Inject;

public class MinePresenter extends BasePresenter<MineView> {

    @Inject
    DataManager dataManager;

    @Inject
    public MinePresenter() {

    }

    /**
     * 加载用户信息
     */
    public void loadUserInfo() {
        subscrib(dataManager.getUserInfo(), new RequestCallback<ResultBase<UserBean>>() {
            @Override
            public void onNext(ResultBase<UserBean> userBeanResultBase) {
                if (userBeanResultBase == null) {
                    getView().tokenOut();
                    getView().showMessage("服务器开小差了，请稍后再试");
                    return;
                }
                //如果token失效
                if (userBeanResultBase.getStatus() == Constants.MSG_STATUS_TOKEN_OUT) {
                    getView().tokenOut();
                    return;
                }
                if (userBeanResultBase.getStatus() != Constants.MSG_STATUS_SUCCESS) {
                    getView().showMessage(userBeanResultBase.getMsg());
                    return;
                }
                if (userBeanResultBase.getData() == null) {
                    getView().showMessage("服务器开小差了，请稍后再试");
                    return;
                }
                UserBean userBean = userBeanResultBase.getData();
                getView().refreshUserInfo(userBean);

            }
        });

    }

    /**
     * 上传图片
     *
     * @param filePath
     */
    public void uploadImage(String filePath) {
        subscrib(dataManager.uploadImage(filePath), new RequestCallback<ResultBase<String>>() {
            @Override
            public void onNext(ResultBase<String> stringResultBase) {
                if (stringResultBase == null) {
                    getView().showMessage("服务器开小差了，请稍后再试");
                    return;
                }
                //如果token失效
                if (stringResultBase.getStatus() == Constants.MSG_STATUS_TOKEN_OUT) {
                    getView().tokenOut();
                    return;
                }
                if (stringResultBase.getStatus() != Constants.MSG_STATUS_SUCCESS) {
                    getView().showMessage(stringResultBase.getMsg());
                    return;
                }
                //获取相对路径
                String url = stringResultBase.getData();

                //刷新用户头像
                getView().refreshUserHead(url);

            }
        });
    }

    /**
     * 注销
     */
    public void logout() {
        subscrib(dataManager.logout(), new RequestCallback<ResultBase>() {
            @Override
            public void onNext(ResultBase resultBase) {
                if (resultBase == null) {
                    getView().showMessage("服务器开小差了，请稍后再试");
                    return;
                }
                //如果token失效
                if (resultBase.getStatus() == Constants.MSG_STATUS_TOKEN_OUT) {
                    getView().tokenOut();
                    return;
                }
                if (resultBase.getStatus() != Constants.MSG_STATUS_SUCCESS) {
                    getView().showMessage(resultBase.getMsg());
                    return;
                }
                //如果返回成功，打开登录界面
                getView().tokenOut();
            }
        });
    }
}
