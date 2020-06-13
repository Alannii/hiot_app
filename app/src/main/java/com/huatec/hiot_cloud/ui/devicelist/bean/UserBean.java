package com.huatec.hiot_cloud.ui.devicelist.bean;

import java.io.Serializable;

public class UserBean implements Serializable {

    private String username;
    private String email;
    private String password;
    private String userType;

    /**
     * 用户头像相对路径
     */
    private String img;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
