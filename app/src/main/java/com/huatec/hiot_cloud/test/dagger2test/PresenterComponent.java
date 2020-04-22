package com.huatec.hiot_cloud.test.dagger2test;

import com.huatec.hiot_cloud.test.mvptest.TestMVPActivity;

import dagger.Component;

@Component(modules = TestModule.class)
public interface PresenterComponent {
    /**
     * 注入方法，由dagger2自动生成
     */
    void inject(TestMVPActivity activity);
}
