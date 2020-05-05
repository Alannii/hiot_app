package com.huatec.hiot_cloud.test.fragmenttest;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.huatec.hiot_cloud.R;

/**
 * 一个简单的{@link Fragment}子类。
 *使用{@link TestFragment#newInstance}工厂方法
 *创建此片段的实例。
 */
public class TestFragment extends Fragment {
    // TODO: 重命名参数参数，选择匹配的名称
    // 片段初始化参数，例如ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO:重命名和更改参数类型
    private int mParam1;

    public TestFragment() {
        //必需的空公共构造函数
    }

    /**
     * 使用此工厂方法创建
     *此片段使用提供的参数。
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment TestFragment.
     */
    // TODO: 重命名和更改参数的类型和数量

    public static TestFragment newInstance(int param1) {
        TestFragment fragment = new TestFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // 扩充这个片段的布局
        View view = inflater.inflate(R.layout.fragment_test, container, false);
        ImageView imageView = view.findViewById(R.id.iv_fragment_test);
        imageView.setImageResource(mParam1);
        return view;
    }
}
