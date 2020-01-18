package  com.home.account.util;



import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * UUID生成工具
 * wyp
 */
public class UUIDUtil {

    private static SimpleDateFormat simpleDate = new SimpleDateFormat("yyyyMMddHHmmss");

    /**
     * 获取以时间为前缀的UUID
     * @return uuid字符串
     */
    public static String getUUID(){

        String uuid = UUID.randomUUID().toString().replace("-", "");
        return  simpleDate.format(new Date())+uuid.substring(0,9);

    }

    public static void main(String[] args) {
        System.out.println(getUUID());
    }
}
