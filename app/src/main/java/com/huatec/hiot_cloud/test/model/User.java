package com.huatec.hiot_cloud.test.model;

import java.io.Serializable;

public class User implements Serializable {
    private String userName;
    private String passwd;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
}
