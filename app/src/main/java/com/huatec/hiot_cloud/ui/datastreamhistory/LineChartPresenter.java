package com.huatec.hiot_cloud.ui.datastreamhistory;

import com.huatec.hiot_cloud.data.DataManager;
import com.huatec.hiot_cloud.data.bean.UpDataStreamSwitchBean;
import com.huatec.hiot_cloud.test.networktest.ResultBase;
import com.huatec.hiot_cloud.ui.base.BasePresenter;

import java.util.List;

import javax.inject.Inject;

/**
 * 通道历史数据Presenter类
 */
public class LineChartPresenter extends BasePresenter<LineCharView> {

    @Inject
    DataManager dataManager;

    @Inject
    public LineChartPresenter() {
    }

    /**
     * 加载通道历史数据
     *
     * @param upDataStreamId
     */
    public void loadUpDataStreamHistory(String upDataStreamId) {
        subscrib(dataManager.getUpdataStreamHistory(upDataStreamId), new RequestCallback<ResultBase<List<UpDataStreamSwitchBean>>>() {
            @Override
            public void onNext(ResultBase<List<UpDataStreamSwitchBean>> upDataStreamSwitchBeanResultBase) {
                super.onNext(upDataStreamSwitchBeanResultBase);
                if (upDataStreamSwitchBeanResultBase.getData() != null) {
                    getView().showDataHistory(upDataStreamSwitchBeanResultBase.getData());
                }
            }
        });
    }
}
