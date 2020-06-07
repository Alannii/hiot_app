package com.huatec.hiot_cloud.ui.base;

public interface BaseView {

    /**
     * 吐司消息
     * @param msg
     */
    void showMessage(String msg);

    /**
     * token失效的处理
     */
    void tokenOut();
}
