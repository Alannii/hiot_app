package com.huatec.hiot_cloud.ui.main;

import android.os.Bundle;
import android.widget.RadioGroup;

import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.huatec.hiot_cloud.R;
import com.huatec.hiot_cloud.ui.base.BaseActivity;


import com.huatec.hiot_cloud.ui.base.BasePresenter;
import com.huatec.hiot_cloud.ui.main.MainViewPagerAdapter;
import com.huatec.hiot_cloud.utils.Constants;


public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //设置ViewPager
        final ViewPager vpMain = findViewById(R.id.vp_main);
        vpMain.setAdapter(new MainViewPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT));
        vpMain.setOffscreenPageLimit(Constants.MAIN_FRAGMENT_COUNT);

        RadioGroup rgMain = findViewById(R.id.rg_main);
        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rg_message:
                        vpMain.setCurrentItem(Constants.MAIN_VIEWPAGER_INDEX_MESSAGE);
                        break;
                    case R.id.rg_equipment:
                        vpMain.setCurrentItem(Constants.MAIN_VIEWPAGER_INDEX_EQUIPMENT);
                        break;
                    case R.id.rg_scene:
                        vpMain.setCurrentItem(Constants.MAIN_VIEWPAGER_INDEX_SCENE);
                        break;
                    case R.id.rg_mine:
                        vpMain.setCurrentItem(Constants.MAIN_VIEWPAGER_INDEX_MINE);
                        break;
                    default:
                        break;
                }
            }
        });


    }

    @Override
    public BasePresenter createPresent() {
        return null;
    }

    @Override
    public void injectIndependies() {
        getActivityComponent().inject(this);
    }
}