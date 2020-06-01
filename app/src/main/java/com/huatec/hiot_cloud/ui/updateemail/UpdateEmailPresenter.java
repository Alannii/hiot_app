package com.huatec.hiot_cloud.ui.updateemail;

import android.text.TextUtils;

import com.huatec.hiot_cloud.data.DataManager;
import com.huatec.hiot_cloud.test.networktest.ResultBase;
import com.huatec.hiot_cloud.ui.base.BasePresenter;
import com.huatec.hiot_cloud.utils.Constants;

import javax.inject.Inject;

class UpdateEmailPresenter extends BasePresenter<UpdateEmailView> {

    @Inject
    DataManager dataManager;

    @Inject
    public UpdateEmailPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    /**
     * 修改邮箱
     *
     * @param email
     */
    public void updateEmail(String email) {
        subscrib(dataManager.updateEmail(email), new RequestCallback<ResultBase<String>>() {
            @Override
            public void onNext(ResultBase<String> stringResultBase) {
                if (stringResultBase.getStatus() == Constants.MSG_STATUS_SUCCESS) {
                    //如果修改成功，跳转到主界面
                    if (stringResultBase != null && stringResultBase.getData() != null) {
                        //弹出修改成功
                        getView().showMessage("修改后的邮箱" + stringResultBase.getData());
                        //跳转到主界面
                        getView().updateSucc();
                    }
                }
                if (stringResultBase.getStatus() == 0) {
                    if (stringResultBase != null && !TextUtils.isEmpty(stringResultBase.getMsg())) {
                        getView().showMessage(stringResultBase.getMsg());
                    }
                }
            }

        });
    }

}