package  com.home.account.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtil {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 参数拼接的post访问方式！
     *
     * @param urlAndParams  末尾带参数，也可以不带参数
     * @param jsonParams    json参数没有，传null,
     * @param requestMethod 请求的方式
     * @return 返回一个流
     */
    public InputStream httpRequest(String urlAndParams, JsonObject jsonParams, String requestMethod) {
        InputStream inputStream = null;
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(urlAndParams).openConnection();
            conn.setRequestMethod(requestMethod);
            conn.setReadTimeout(300);
            conn.setConnectTimeout(1000);
            if (conn.getResponseCode() == 200)
                inputStream = conn.getInputStream();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return inputStream;
    }

    //将流转成String
    public String InputStreamToT(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            return null;
        }
        //数组输出流
        BufferedReader  bfd = new BufferedReader (new InputStreamReader(inputStream));
        StringBuffer stf = new StringBuffer();
        String s = new String();
        while ((s = bfd.readLine()) != null){
           stf.append(s);
        }
      return  stf.toString();

    }

    public static void main(String[] args) throws IOException {
        HttpUtil httpUtil = new HttpUtil();
        FileInputStream inputStream = new FileInputStream(new File("D://FTP//MB17534772019112210393300001T.TXT"));
        JSONObject s = JSONObject.parseObject(httpUtil.InputStreamToT(inputStream));
        System.out.println(s);
    }
}
