package com.huatec.hiot_cloud.test.networktest;

import java.io.Serializable;

public class ResultBase<E> implements Serializable {

    E data;

    int status;

    String msg;

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
