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
}
