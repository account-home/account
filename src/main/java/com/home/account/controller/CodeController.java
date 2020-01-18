package  com.home.account.controller;

import com.home.account.util.CodeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;


/**
 * 验证码控制器
 */
@RestController
public class CodeController {

   private  static Logger logger = LoggerFactory.getLogger(CodeController.class);

    @RequestMapping("/getCode")
    public void getCode(HttpServletResponse response,HttpServletRequest request)throws IOException {
        // 调用工具类生成的验证码和验证码图片
        Map<String, Object> codeMap = CodeUtil.generateCodeAndPic();
        //将四位数字的验证码保存到Session中。
        HttpSession session =request.getSession();
        session.setAttribute("code", codeMap.get("code").toString());
        // 禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", -1);
        response.setContentType("image/jpeg");
        OutputStream outputStream = response.getOutputStream();
        ImageIO.write((RenderedImage) codeMap.get("codePic"), "jpeg", outputStream);
        outputStream.close();
    }

    /**
     * 验证验证码的方法
     * @param request 请求体
     * @return error--验证码错误 或者succeed--通过
     */
    @PostMapping("/getCheckCode")
    public String getCheckCode(HttpServletRequest request) {
        String code = "";
        String codeSession = "";
        try {
            code = request.getParameter("code");
            HttpSession session = request.getSession();
            codeSession = (String) session.getAttribute("code");
        }catch(Exception e){
            logger.error(e.toString());
            return "error";
        }
        if(code != "" && codeSession.equalsIgnoreCase(code)){
         return "succeed";
        }
        return  "error";
    }

}
