package com.home.account;

import com.alibaba.fastjson.JSONObject;
import com.home.account.entity.Bills;
import com.home.account.entity.User;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.*;
import java.util.Properties;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class Test {

    public static void main(String[] args) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("user_id",999);
        jsonObject.put("user_autograph",999);
        jsonObject.put("user_iphone",999);
        jsonObject.put("biil_id",888);
        jsonObject.put("user_id",777);

        Bills bill = JSONObject.toJavaObject(jsonObject,Bills.class);
        User U = JSONObject.toJavaObject(jsonObject, User.class);
        System.out.println(bill.getUser_id()+"?"+bill.getBiil_id());
        System.out.println(U.getUser_id()+U.getUser_autograph()+U.getUser_iphone());

        ResourceBundle resource = ResourceBundle.getBundle("config/antgisoa");
        String key = resource.getString("ftpServer");
        System.out.println(key);








    }
}
