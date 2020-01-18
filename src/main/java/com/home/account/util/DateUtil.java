package  com.home.account.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 */
public class DateUtil {

    private static SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static SimpleDateFormat sf_day = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
    /**
     * 将日期转为字符串格式
     * @param date date对象
     * @return 返回一个日期的字符串
     */
     public static String dateToString(Date date){
         return  sf.format(date);
     }

    /**
     * 将字符串转为date对象
     * @param string 符合时间格式的字符串
     * @return 返回一个日期对象
     */
    public static Date stringToDate(String  string){
        try {
            Date dateTime = sf.parse(string);
            return dateTime;
        }catch (ParseException p){
            return  null;
        }
    }

    //有当天0点格式化，第二天0点格式化
    public static void main(String[] args) throws ParseException {
        Date day = new Date();
        String   date1 = sf_day.format(day) ;
        System.out.println(date1);

        //日期+1
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(day);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        day = calendar.getTime();
        String   date2 = sf_day.format(day) ;
        System.out.println(date2);

    }

}
