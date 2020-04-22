package com.huatec.hiot_cloud.test.dagger2test;

import dagger.Module;
import dagger.Provides;

@Module
public class TestModule {
    @Provides
    public ThirdObj getThird(){return new ThirdObj();}
}
