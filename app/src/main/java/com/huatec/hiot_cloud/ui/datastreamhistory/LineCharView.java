package com.huatec.hiot_cloud.ui.datastreamhistory;

import com.huatec.hiot_cloud.data.bean.UpDataStreamSwitchBean;
import com.huatec.hiot_cloud.ui.base.BaseView;

import java.util.List;

/**
 * 通道历史数据view层接口
 */
public interface LineCharView extends BaseView {

    /**
     * 把返回的通道历史数据显示到图标中
     *
     * @param dataList
     */
    void showDataHistory(List<UpDataStreamSwitchBean> dataList);
}
