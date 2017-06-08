package app.com.lsl.imagelabelerapp.lsl.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/** 获取时间
 * Created by M1308_000 on 2017/6/8.
 */

public class DateUtils {

    /**
     * 获取当前时间
     * @return
     */
    public static String getNowTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        return "" + dateFormat.format(date);
    }
}
