package com.huatec.hiot_cloud.ui.mine;

import com.huatec.hiot_cloud.test.networktest.UserBean;
import com.huatec.hiot_cloud.ui.base.BaseView;

public interface MineView extends BaseView {

    /**
     * 刷新用户信息
     *
     * @param userBean
     */
    void refreshUserInfo(UserBean userBean);

    /**
     * 刷新用户头像
     *
     * @param url
     */
    void refreshUserHead(String url);

    /**
     * 重新登录的处理的操作
     */
    void tokenOut();

    /**
     * 修改密码的处理的操作
     */
    void updatePassword();

    /**
     * 修改邮箱的处理的操作
     */
    void updateEmail();
}
