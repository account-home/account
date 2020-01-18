package  com.home.account.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 加密工具类
 */
public class Encryption {
 private static Logger log = LoggerFactory.getLogger(Encryption.class);
    /**
     * MD5加密
     * @param str
     * @return
     */
    public String getMD5(String str)  {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            //生成随机东东 不可逆
            byte[] bytes = md.digest(str.getBytes());
            String string = new BASE64Encoder().encode(bytes);
            return string;
        }catch(NoSuchAlgorithmException no){
            log.error("com.home.account.util.Encryption.getMD5:加密失败");
            return null;
        }
    }

    /**
     * Base64 加密
     * @param str 需要加密的字符串
     * @return
     */
    public static String getBase64(String str){
        byte[] b = null;
        String s = null;
        try {
            b = str.getBytes(StandardCharsets.UTF_8);
        } catch (Exception e) {
            log.error("com.home.account.util.Encryption.getBase64:加密失败");
            e.printStackTrace();
        }
        if (b != null) {
            s = new BASE64Encoder().encode(b);
        }
        return s;
    }
    /**
     * Base64 解密
     * @param str 已经使用base64加密过的字符串
     * @return 字符串
     */
    public static String removeBase64(String str){
        byte[] b = null;
        String result = null;
        if (str != null) {
            BASE64Decoder base64 = new BASE64Decoder();
            try {
                b = base64.decodeBuffer(str);
                result = new String(b, StandardCharsets.UTF_8);
            } catch (Exception e) {
                log.error("com.home.account.util.Encryption.removeBase64:解密失败");
            }
        }
        return result;
    }

    public static void main(String[] args) {
       new Encryption().removeBase64("99999999999999999");
    }
}
