/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.huatec.hiot_cloud.injection.component;


import com.huatec.hiot_cloud.injection.PerActivity;
import com.huatec.hiot_cloud.injection.module.ActivityModule;
import com.huatec.hiot_cloud.test.mvptest.TestMVPActivity;
import com.huatec.hiot_cloud.test.networktest.TestNetworkPackActivity;
import com.huatec.hiot_cloud.ui.SplashActivity;
import com.huatec.hiot_cloud.ui.devicelist.DeviceListFragment;
import com.huatec.hiot_cloud.ui.login.LoginActivity;
import com.huatec.hiot_cloud.ui.main.MainActivity;
import com.huatec.hiot_cloud.ui.mine.MineFragment;
import com.huatec.hiot_cloud.ui.register.RegisterActivity;
import com.huatec.hiot_cloud.ui.scan.ScanActivity;
import com.huatec.hiot_cloud.ui.updateemail.UpdateEmailActivity;
import com.huatec.hiot_cloud.ui.updatepasswd.UpdatePasswdActivity;

import dagger.Component;

/**
 * A base component upon which fragment's components may depend.
 * Activity-level components should extend this component.
 * <p>
 * Subtypes of ActivityComponent should be decorated with annotation:
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);

    void inject(TestMVPActivity testMVPActivity);

    void inject(TestNetworkPackActivity testNetworkPackActivity);

    void inject(LoginActivity loginActivity);

    void inject(SplashActivity splashActivity);

    void inject(RegisterActivity registerActivity);

    void inject(MineFragment mineFragment);

    void inject(DeviceListFragment deviceListFragment);

    void inject(UpdateEmailActivity updateEmailActivity);

    void inject(UpdatePasswdActivity updatePasswdActivity);

    void inject(ScanActivity scanActivity);

    @Component.Builder
    interface ActivityComponentBuilder {

        ActivityComponent build();

        ActivityComponentBuilder applicationComponent(ApplicationComponent applicationComponent);

        ActivityComponentBuilder activityModule(ActivityModule activityModule);
    }
}
