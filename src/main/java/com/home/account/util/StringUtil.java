package  com.home.account.util;


import com.alibaba.fastjson.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;


/**
 * 字符串工具
 * wyp
 */
public class StringUtil {

    /**
     * 将json对象转为字符串
     * @param json
     * @return
     */
    public static String jsonToString(JSONObject json){
        if(json==null|json.equals("")){
            return  null;
        }
        return  JSONObject.toJSONString(json);
    }

    /**
     * 将对象转为字符串
     * @param obj  class对象
     * @return 该对象的json格式字符串
     */
    public static String objectToString(Object obj){
        if(obj==null||obj.equals("")){
            return  null;
        }
        return  JSONObject.toJSONString(obj);

    }

    /**
     * 将对象转为Json
     * @param obj  class对象
     * @return JSONObject对象
     */
    public static JSONObject objectToJson(Object obj){
        if(obj==null|obj.equals("")){
            return  null;
        }
        return JSONObject.parseObject(objectToString(obj));
    }
    /**
     * 将对象转为Json
     * @param obj  class对象
     * @return 不返回空，容易出错
     */
    public static String objToString(Object obj){
        if(obj !=null|!obj.equals("")){
            return  obj.toString();
        }
        return "";
    }
    /**
     * 将对象转为Json
     * @param json  JSONObject对象
     * @return class对象
     */
    public static Object JsonToObject(JSONObject json,Class classes){
        if(json==null|json.equals("")){
            return  null;
        }
        return JSONObject.toJavaObject(json,classes);
    }
    //这是时间格式，
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
    /**
     * 方法格式就是前段加时间后段家uuid，方便区分
     * @return 一个不同的uuid值
     */
    public static String getUUID(){
        return sdf.format(new Date()) + UUID.randomUUID().toString().substring(24);
    }



}
