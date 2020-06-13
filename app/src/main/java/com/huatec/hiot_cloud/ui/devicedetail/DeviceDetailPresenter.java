package com.huatec.hiot_cloud.ui.devicedetail;

import com.huatec.hiot_cloud.data.DataManager;
import com.huatec.hiot_cloud.data.bean.DeviceDetailBean;
import com.huatec.hiot_cloud.test.networktest.ResultBase;
import com.huatec.hiot_cloud.ui.base.BasePresenter;
import com.huatec.hiot_cloud.utils.Constants;

import javax.inject.Inject;

public class DeviceDetailPresenter extends BasePresenter<DeviceDetailView> {

    @Inject
    public DeviceDetailPresenter() {
    }

    @Inject
    DataManager dataManager;

    /**
     * 加载设备详情
     *
     * @param deviceId
     */
    public void loadDeviceDetail(String deviceId) {
        subscrib(dataManager.getDeviceDetail(deviceId), new RequestCallback<ResultBase<DeviceDetailBean>>() {
            @Override
            public void onNext(ResultBase<DeviceDetailBean> deviceDetailBeanResultBase) {
                super.onNext(deviceDetailBeanResultBase);
                if (deviceDetailBeanResultBase.getData() == null) {
                    getView().showMessage("无设备详情");
                }
                getView().setDeviceDetail(deviceDetailBeanResultBase.getData());
            }
        });
    }

    /**
     * 控制通道开关
     *
     * @param downDataStreamId
     * @param isChecked
     */
    public void changeSwitch(String downDataStreamId, boolean isChecked) {
        int status = isChecked ? Constants.SWITCH_STATUS_ON : Constants.SWITCH_STATUS_OFF;
        subscrib(dataManager.changeSwitch(downDataStreamId, status), new RequestCallback<ResultBase>() {
            @Override
            public void onNext(ResultBase resultBase) {
                super.onNext(resultBase);
                getView().showMessage("操作成功");
            }
        });
    }
}
