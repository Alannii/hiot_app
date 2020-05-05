package com.huatec.hiot_cloud.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.huatec.hiot_cloud.R;
import com.huatec.hiot_cloud.ui.base.BaseFragment;
import com.huatec.hiot_cloud.ui.base.BasePresenter;

public class MessageFragment extends BaseFragment {

    /**
     * 创建fragment实例
     * @return fragment
     */
    public static MessageFragment newInstance(){
        MessageFragment fragment = new MessageFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         View view = inflater.inflate(R.layout.fragment_main,container,false);
         return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView tv = view.findViewById(R.id.tv_main_fragment);
        tv.setText("消息");
    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void injectDependencies() {

    }
}
