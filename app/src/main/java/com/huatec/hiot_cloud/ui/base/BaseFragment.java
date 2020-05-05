package com.huatec.hiot_cloud.ui.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public abstract class BaseFragment<V extends BaseView,P extends BasePresenter<V>> extends Fragment implements BaseView {

    private P presenter;

    public  abstract View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);

    public abstract P createPresenter();

    public abstract void injectDependencies();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        /**
         * 创建View
         */

        View view = initView(inflater,container,savedInstanceState);
        injectDependencies();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /**
         * View创建完成后触发
         */

        presenter = createPresenter();
        if (presenter != null){
            presenter.setView((V)this);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (presenter != null){
            presenter = null;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

}
