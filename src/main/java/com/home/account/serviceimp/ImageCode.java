package  com.home.account.serviceimp;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

@Service
public class ImageCode {

    public BufferedImage getImageCode(HttpServletRequest request){
        BufferedImage bi = new BufferedImage(68,22,BufferedImage.TYPE_INT_RGB);
        Graphics g = bi.getGraphics();//画笔对象
        Color c = new Color(76, 141,255);//颜色对象
        g.setColor(c);//给画笔赋上颜色
        g.fillRect(0,0,430,30);//画一个外框
        char [] ch = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
        Random r = new Random();
        int len = ch.length,index;
        StringBuffer sb = new StringBuffer();
        for (int i=0;i<4;i++){
            index = r.nextInt(len);
            g.setColor(new Color(r.nextInt(88),r.nextInt(188),r.nextInt(255)));
            g.drawString(ch[index]+"",(i*15)+3,18);//画随机字符
            sb.append(ch[index]);
        }

        String checkCode = sb.toString();
        request.getSession().setAttribute("checkCode",checkCode);

        return bi;
//        ImageIO.write(bi,"JPG",response.getOutputStream());
    }
}