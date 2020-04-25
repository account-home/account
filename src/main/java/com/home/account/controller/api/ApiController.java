package com.home.account.controller.api;

import com.alibaba.fastjson.JSON;
import com.home.account.config.BaseConfig;
import com.home.account.config.BookConfig;
import com.home.account.entity.Result;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * 所有api接口都写在这里
 * 多余操作方法写在api.ServiceUtils中
 */
@RequestMapping("api")
@RestController
public class ApiController {
    @Autowired
    private BookConfig bookConfig;
    @Autowired
    private BaseConfig baseConfig;
    private Result result = new Result();

    @RequestMapping("book")
    public Result getBookInfo(){
        bookConfig.setIp(BaseConfig.get("ftp.url"));
        result.setData(bookConfig);
        result.setSuccess(true);
        return result;
    }

    /**
     * 批量打印目录,模板放在template\print\13里面,
     * 模板和本数据库信息不同会打印出空白pdf
     */
    @PostMapping (value = "printmanycatalog", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public  Result printManyCataLog( @RequestBody Map map) throws JRException {

        Result result = new Result();
        //预处理数据
        if (map.get("archnos")==null||map.get("jasperName")==null){
            result.setMessage("案卷编号为空或模板名称为空");
            result.setSuccess(false);
            return result;
        }
        String archnos = map.get("archnos").toString();
        String jasperName = map.get("jasperName").toString();
        String[] archnoArray = archnos.split(",");


        String  modelPath  ="D:\\java-app\\apache-tomcat\\apache-tomcat-8.5.33-newoa\\template\\print\\13\\test.jasper" ;
        byte[]  bytes = JasperRunManager.runReportToPdf(modelPath, null, this.getConn());
        result.setSuccess(true);
        result.setData(bytes.length);
        return result;
    }


    // 数据库获取连接对象
    public  Connection getConn(){
        BaseConfig baseConfig = new BaseConfig();
        Connection conn =null;
       String  url=baseConfig.get("jdbc.url");
       String   driver=baseConfig.get("jdbc.driver");
        String username=baseConfig.get("jdbc.username");
        String  password=baseConfig.get("jdbc.password");

        if(conn == null){
            try {
                Class.forName(driver);
                conn = DriverManager.getConnection(url, username, password);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }

}
