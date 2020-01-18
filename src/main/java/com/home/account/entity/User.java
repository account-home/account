package com.home.account.entity;

import com.home.account.util.UUIDUtil;

import java.util.List;

public class User {
    private String user_id = UUIDUtil.getUUID();
    private String user_name;
    private String user_autograph;
    private String user_iphone;
    private String user_assets;
    private String user_password;
    private List<Detail> details;


    public List<Detail> getDetails() {
        return details;
    }

    public void setDetails(List<Detail> details) {
        this.details = details;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_autograph() {
        return user_autograph;
    }

    public void setUser_autograph(String user_autograph) {
        this.user_autograph = user_autograph;
    }

    public String getUser_iphone() {
        return user_iphone;
    }

    public void setUser_iphone(String user_iphone) {
        this.user_iphone = user_iphone;
    }

    public String getUser_assets() {
        return user_assets;
    }

    public void setUser_assets(String user_assets) {
        this.user_assets = user_assets;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public User(String user_name, String user_autograph, String user_iphone, String user_assets, String user_password) {
        this.user_name = user_name;
        this.user_autograph = user_autograph;
        this.user_iphone = user_iphone;
        this.user_assets = user_assets;
        this.user_password = user_password;
    }

    public User( ) {
    }
}
