package app.com.lsl.imagelabelerapp.lsl.utils;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/** 获取时间
 * Created by M1308_000 on 2017/6/8.
 */

public class DateUtils {

    public static Date getDataToString(String s) {
        ParsePosition position = new ParsePosition(0);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date data = simpleDateFormat.parse(s, position);
        return data;
    }

    /**
     * 获取当前时间
     * @return
     */
    public static String getNowTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        return "" + dateFormat.format(date);
    }

    /**
     * 判断日期是否为今天
     * @param date
     * @return
     * @throws ParseException
     */
    public static boolean IsToday(String date) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        Date date1 = new Date(System.currentTimeMillis());
        calendar.setTime(date1);

        Calendar cal = Calendar.getInstance();
        Date d = getDateFormat().parse(date);
        cal.setTime(d);

        if (cal.get(Calendar.YEAR) == (calendar.get(Calendar.YEAR))) {
            int diffDay = cal.get(Calendar.DAY_OF_YEAR)
                    - calendar.get(Calendar.DAY_OF_YEAR);

            if (diffDay == 0) {
                return true;
            }
        }
        return false;
    }

    public static SimpleDateFormat getDateFormat() {
        if (null == DateLocal.get()) {
            DateLocal.set(new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA));
        }
        return DateLocal.get();
    }

    private static ThreadLocal<SimpleDateFormat> DateLocal = new ThreadLocal<>();
}
