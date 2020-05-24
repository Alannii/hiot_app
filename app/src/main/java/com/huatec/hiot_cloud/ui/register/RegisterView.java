package com.huatec.hiot_cloud.ui.register;

import com.huatec.hiot_cloud.ui.base.BaseView;

public interface RegisterView extends BaseView {

    void registerSucc(String username, String passwd);

    void loginSucc();
}
