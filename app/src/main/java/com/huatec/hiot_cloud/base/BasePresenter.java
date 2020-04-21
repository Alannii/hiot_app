package com.huatec.hiot_cloud.base;

public class BasePresenter<V extends  BaseView> {

    private  V view;

    public boolean viewAttached(){
        return view!=null;
    }

    public void destory(){
        if (view!=null){
            view = null;
        }
    }

    public V getView() {
        return view;
    }

    public void setView(V view) {
        this.view = view;
    }
}
