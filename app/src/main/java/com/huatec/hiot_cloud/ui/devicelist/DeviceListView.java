package com.huatec.hiot_cloud.ui.devicelist;

import com.huatec.hiot_cloud.ui.base.BaseView;
import com.huatec.hiot_cloud.ui.devicelist.bean.DeviceBean;

import java.util.List;

public interface DeviceListView extends BaseView {
    /**
     * 显示设备列表
     *
     * @param deviceBeanList
     */
    void showDevice(List<DeviceBean> deviceBeanList);
}
