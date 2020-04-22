package com.home.account.config;

import java.util.ResourceBundle;

public class BaseConfig {

    public static  String  get(String key){
        ResourceBundle rs = ResourceBundle.getBundle("config/baseconfig") ;
        return rs.getString(key);
    }
}
