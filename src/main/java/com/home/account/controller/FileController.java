package  com.home.account.controller;


import com.alibaba.fastjson.JSONObject;
import com.home.account.ftp.Ftp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;


/**
 * 文件操作控制器
 */
@RestController
@Component
@RequestMapping("/file")
public class FileController {


    @Autowired
    private  Ftp ftp;



    @RequestMapping("/getFile")
    public void getFile( HttpServletResponse response)  {

        try{
            OutputStream outputStream =response.getOutputStream();
            InputStream inputStream = new FileInputStream(new File("D://FTP//MB17534772019112210393300001T.TXT"));
            // 清空response
            response.reset();
            response.setContentType("bin");
            // 设置response的 请求头 Header
            response.setHeader("Content-Disposition","attachment; filename=888.txt");
            byte[]  b = new byte[1024];
            int len = 0;
            while ((len = inputStream.read(b)) != -1){
                outputStream.write(b,0,len);
            }
            outputStream.flush();
            inputStream.close();
        }catch (IOException io){
        }
    }

    /**
     * 解决文件名重复的问题
     * 时间+文件名称，较为靠谱
     */


    /**
     * 上传文件 文件名称重复问题
     * @param fileName 文件名称
     */
    @RequestMapping("/upFile")
    public void upFile(@Param("fileName") String  fileName )   {
        try{
            InputStream inputStream = new FileInputStream(new File("D://FTP//MB17534772019112210393300001T.TXT"));
            ftp.uploadFile(inputStream,fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据文件名称来下载文件，
     * @param fileName 文件名
     * @param response 文件流
     */
    @RequestMapping("/downFile")
    public void downFile(@Param("fileName") String  fileName,HttpServletResponse response )   {
        try{
            OutputStream outputStream = response.getOutputStream();
            outputStream = ftp.downFile(outputStream,fileName,"2020/01/03");
            response.setContentType("bin");
            response.setHeader("Content-disposition", "attachment; filename=" + new String(fileName.getBytes("utf-8"), "ISO8859-1"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
