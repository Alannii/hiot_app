package com.huatec.hiot_cloud.test.mvptest;

import com.huatec.hiot_cloud.ui.base.BasePresenter;
import com.huatec.hiot_cloud.test.User;

import javax.inject.Inject;


public class TestPresenter extends BasePresenter<TestView> {

    TestView view;


    @Inject
    public TestPresenter(){}

    public void login(User user){

        if ("lisi".equals(user.getUserName()) && "123".equals(user.getPasswd())) {
            view.showMessage("登录成功");

        } else {
            view.showMessage("登录失败");
        }
    }

    public TestView getView() {
        return view;
    }

    public void setView(TestView view) {
        this.view = view;
    }

    public void destory(){
        if (view!=null){
            view = null;
        }
    }
}
