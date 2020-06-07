package com.huatec.hiot_cloud.ui.scan;

import com.huatec.hiot_cloud.data.DataManager;
import com.huatec.hiot_cloud.test.networktest.ResultBase;
import com.huatec.hiot_cloud.ui.base.BasePresenter;

import javax.inject.Inject;

/**
 * 扫一扫模块Present类
 */
public class ScanPresenter extends BasePresenter<ScanView>{

    @Inject
    public ScanPresenter() {
    }

    @Inject
    DataManager dataManager;

    /**
     * 绑定设备
     * @param deviceId
     */
    public void bindDevice(String deviceId) {
        subscrib(dataManager.bindDevice(deviceId), new RequestCallback<ResultBase>() {
            @Override
            public void onNext(ResultBase resultBase) {
                super.onNext(resultBase);
                //吐司绑定成功
                getView().showMessage("绑定成功");
                //关闭扫一扫界面
                getView().bindDeviceSucc();
            }
        });
    }
}
