package com.huatec.hiot_cloud.ui.base;

import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

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

    public <T> void subscrib(Observable<T> observable, final RequestCallback<T> callback) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Observer<T>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        callback.onSubscribe(d);
                    }

                    @Override
                    public void onNext(T t) {
                        callback.onNext(t);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(e);
                    }

                    @Override
                    public void onComplete() {
                        callback.onComplete();
                    }
                });
    }

    public abstract class RequestCallback<T> {

        public void onSubscribe(Disposable d) {
        }

        public abstract void onNext(T t);

        public void onError(Throwable e) {
            Log.e("RequestCallback", "onError: ", e);
        }

        public void onComplete() {
        }
    }
}
