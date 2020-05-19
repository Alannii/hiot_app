package com.huatec.hiot_cloud.test.networktest;

import com.huatec.hiot_cloud.ui.base.BaseView;

/**
 * 网络封装测试MVP架构view层接口
 */
public interface TestNetworkView extends BaseView {
    void showToken(String token);

    void showMessage(String msg);
}
