package com.huatec.hiot_cloud.ui.scan;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.widget.TextView;


import com.huatec.hiot_cloud.R;
import com.huatec.hiot_cloud.ui.base.BaseActivity;
import com.king.zxing.CaptureHelper;
import com.king.zxing.OnCaptureCallback;
import com.king.zxing.ViewfinderView;
import com.king.zxing.camera.FrontLightMode;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ScanActivity extends BaseActivity<ScanView,ScanPresenter> implements ScanView, OnCaptureCallback {

    /**
     * 二维码帮助工具类
     */
    private CaptureHelper mCaptureHelper;

    @Inject
    ScanPresenter scanPresenter;

    @BindView(R.id.surfaceView)
    SurfaceView surfaceView;

    @BindView(R.id.viewfinderView)
    ViewfinderView viewfinderView;

    @BindView(R.id.tv_light)
    TextView tvLight;

    @BindView(R.id.tv_album)
    TextView tvAlbum;

    @Override
    public ScanPresenter createPresent() {
        return scanPresenter;
    }

    @Override
    public void injectIndependies() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        ButterKnife.bind(this);
        initView();//需要先初始化
    }

    @OnClick({R.id.tv_light, R.id.tv_album})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_light:
                break;
            case R.id.tv_album:
                break;
        }
    }

    /**
     * 初始化控件
     */
    private void initView(){
        //创建扫描工具类
        mCaptureHelper = new CaptureHelper(this, surfaceView, viewfinderView, null);

        //设置属性
        mCaptureHelper.vibrate(true)//震动
                .frontLightMode(FrontLightMode.AUTO)//设置闪光灯自动亮
                .tooDarkLux(45f)//设置光线太暗时，自动触发开启闪光灯的照度值
                .brightEnoughLux(100f)//设置光线足够明亮时，自动触发关闭闪光灯的照度值
                .continuousScan(true);//持续扫描

        //设置扫描结果回调
        mCaptureHelper.setOnCaptureCallback(this);

        //执行创建
        mCaptureHelper.onCreate();
    }

    @Override
    public boolean onResultCallback(String result) {
        scanPresenter.bindDevice(result);
        return false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mCaptureHelper.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mCaptureHelper.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCaptureHelper.onDestroy();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mCaptureHelper.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public void bindDeviceSucc() {
        finish();
    }
}