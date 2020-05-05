package com.huatec.hiot_cloud.ui.base;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.huatec.hiot_cloud.App;
import com.huatec.hiot_cloud.injection.component.ActivityComponent;
import com.huatec.hiot_cloud.injection.component.ApplicationComponent;

import com.huatec.hiot_cloud.injection.component.DaggerActivityComponent;
import com.huatec.hiot_cloud.injection.module.ActivityModule;

public abstract class BaseActivity<V extends BaseView,P extends BasePresenter<V>> extends AppCompatActivity implements BaseView {

    private ActivityComponent mActivityComponent;

    private P presenter;

    public abstract P createPresent();

    public abstract void injectIndependies();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectIndependies();
        presenter = createPresent();
        if (presenter != null){
            presenter.setView((V)this);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.destory();
    }

    public ActivityComponent getActivityComponent() {
        if (null == mActivityComponent) {
            mActivityComponent = DaggerActivityComponent.builder()
                    .activityModule(getActivityModule())
                    .applicationComponent(getApplicationComponent())
                    .build();
        }
        return mActivityComponent;
    }

    public ApplicationComponent getApplicationComponent() {

        Application application = getApplication();
        App app = (App) application;
        return app.component();
    }

    /**
     * Get an Activity module for dependency injection.
     */
    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }
}
