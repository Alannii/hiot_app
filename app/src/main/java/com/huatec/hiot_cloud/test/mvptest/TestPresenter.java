package com.huatec.hiot_cloud.test.mvptest;

import com.huatec.hiot_cloud.test.model.User;

public class TestPresenter {

    TestView view;

    public TestPresenter(TestView view){
        this.view =view;
    }

    public void login(User user){

        if ("lisi".equals(user.getUserName()) && "123".equals(user.getPasswd())) {
            view.showMessage("登录成功");

        } else {
            view.showMessage("登录失败");
        }
    }
}
