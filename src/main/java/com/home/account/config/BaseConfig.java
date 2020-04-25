package com.home.account.config;

import org.springframework.stereotype.Component;

import java.util.ResourceBundle;

@Component
public class BaseConfig {

    public static  String  get(String key){

        ResourceBundle rs = ResourceBundle.getBundle("config/baseconfig") ;
        return rs.getString(key);

    }

}
