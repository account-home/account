package  com.home.account.controller;

import com.home.account.util.HttpUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;

/**
 * 测试控制器
 */
@Controller
@RequestMapping("/test")
public class TestController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/loginSucceed")
    public String login() {
        return "/views/loginSucceed.html";
    }

    @RequestMapping(value = "/error")
    public String error() {
        return "/views/error.html";
    }


    @RequestMapping(value = "/getString")
    @ResponseBody
       public String  getString() {
        return "modelAndView";
    }


    // Parameters 参数传递
    @RequestMapping("/{name}")
    public void showMapPdf(HttpServletResponse response,@PathVariable("name") String name) throws IOException {

        //1.获取编译后jasper模板文件流
        InputStream reportStream = this.getClass().getClassLoader().getResourceAsStream("jasper/"+name+".jasper");
        //2.获取输出流
        String  str = new HttpUtil().InputStreamToT(reportStream);
        OutputStream outputStream = response.getOutputStream();
        //2.创建参数值，填充参数
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("userId", "10001");
        hashMap.put("userName", "happy");
        hashMap.put("userAge", 18);
        hashMap.put("userSex", "1");
        hashMap.put("userAddress", "北京市");
        //3.调用Jasper报表引擎
        try {
            outputStream = response.getOutputStream();
            JasperRunManager.runReportToPdfStream(reportStream, outputStream, hashMap);
            response.setContentType("application/pdf");
            outputStream.close();
        } catch (JRException | IOException e) {
            logger.info(e.toString());
        }
    }
}

