package Time;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *@Author 南木
 *@Blog https://lucas-liang.github.io/
 *@Time 2019/1/4
 *@Version 1.0
 */
public class TimeUtils {

    private final static long minute = 60 * 1000;// 1分钟
    private final static long hour = 60 * minute;// 1小时
    private final static long day = 24 * hour;// 1天
    private final static long month = 31 * day;// 月
    private final static long year = 12 * month;// 年


    /**
     * @Description date类型转换为String类型
     * @Date 2019/1/4 16:48
     * @param data Date类型的时间
     * @param formatType 时间格式
     * @return
     **/
    public static String dateToString(Date data, String formatType) {
        return new SimpleDateFormat(formatType).format(data);
    }

    /**
     * @Description  long类型转换为String类型
     * @Date 2019/1/4 16:49
     * @param currentTime long类型的时间
     * @param formatType 时间格式
     * @return
     **/
    public static String longToString(long currentTime, String formatType)
            throws ParseException {
        Date date = longToDate(currentTime, formatType);
        String strTime = dateToString(date, formatType);
        return strTime;
    }

    /**
     * @Description string类型转换为date类型
     * @Date 2019/1/4 16:50
     * @param strTime string类时间
     * @param formatType 时间格式
     * @return strTime的时间格式必须要与formatType的时间格式相同
     **/
    public static Date stringToDate(String strTime, String formatType)
            throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(formatType);
        Date date = null;
        date = formatter.parse(strTime);
        return date;
    }

    /**
     * @Description long转换为Date类型
     * @Date 2019/1/4 16:52
     * @param currentTime long类时间
     * @param formatType 时间格式
     * @return
     **/
    public static Date longToDate(long currentTime, String formatType)
            throws ParseException {
        Date dateOld = new Date(currentTime);
        String sDateTime = dateToString(dateOld, formatType);
        Date date = stringToDate(sDateTime, formatType);
        return date;
    }

    /**
     * @Description string类型转换为long类型
     * @Date 2019/1/4 16:53
     * @param strTime String类时间
     * @param formatType 时间格式
     * @return
     **/
    public static long stringToLong(String strTime, String formatType)
            throws ParseException {
        Date date = stringToDate(strTime, formatType);
        if (date == null) {
            return 0;
        } else {
            long currentTime = dateToLong(date);
            return currentTime;
        }
    }

    /**
     * @Description date类型转换为long类型
     * @Date 2019/1/4 16:54
     * @param  date date类时间
     * @return
     **/
    public static long dateToLong(Date date) {
        return date.getTime();
    }


   /**
    * @Description 返回yyyy-MM-dd HH:mm:ss的字符串
    * @Date 2019/1/4 14:58
    * @Param
    * @return String
    **/
    public static String getCurrentTimeStr() {
        return getCurrentTimeStr("yyyy-MM-dd HH:mm:ss");
    }

   /**
    * @Description 返回 yyyy-MM-dd的字符
    * @Date 2019/1/4 14:58
    * @Param
    * @return String
    **/
    public static String getCurrentTimeYear() {
        return getCurrentTimeStr("yyyy-MM-dd");
    }

   /**
    * @Description 返回 HH:mm:ss的字符
    * @Date 2019/1/4 14:58
    * @Param
    * @return String
    **/
    public static String getCurrentTimeStrHour() {
        return getCurrentTimeStr("HH:mm:ss");
    }

    /**
     * @Description 返回自定义时间格式的字符
     * @Date 2019/1/4 14:59
     * @Param custom  自定义时间格式
     * @return String
     **/
    public static String getCurrentTimeStr(String custom) {
        Calendar curCalendar = Calendar.getInstance();
        DateFormat df = new SimpleDateFormat(custom);
        return df.format(curCalendar.getTime());
    }

   /**
    * @Description 将时间戳转成格式化时间 返回 yyyy-MM-dd HH:mm:ss
    * @Date 2019/1/4 14:57
    * @Param milliseconds  时间
    * @return String
    **/
    public static String getFormatTime(long milliseconds) {
        return getFormatTime(milliseconds,"yyyy-MM-dd HH:mm:ss");
    }

    /**
     * @Description 将时间戳转成格式化时间 返回 yyyy-MM-dd
     * @Date 2019/1/4 15:01
     * @Param milliseconds  时间
     * @return String
     **/
    public static String getFormatTimeYear(long milliseconds) {
        return getFormatTime(milliseconds,"yyyy-MM-dd");
    }

    /**
     * @Description 将时间戳转成格式化时间 返回 HH:mm:ss
     * @Date 2019/1/4 15:01
     * @Param milliseconds   时间
     * @return String
     **/
    public static String getFormatTimeHour(long milliseconds) {
        return getFormatTime(milliseconds,"HH:mm:ss");
    }

    /**
     * @Description 根据自定义参数来返回相关格式的时间
     * @Date 2019/1/4 15:01
     * @Param milliseconds  时间
     * @Param custom       自定义时间
     * @return String
     **/
    public static String getFormatTime(long milliseconds,String custom) {
        SimpleDateFormat sdf = new SimpleDateFormat(custom);
        return sdf.format(new Date(milliseconds));
    }

    /**
     * @Description 返回文字描述的日期
     * @Date 2019/1/4 15:12
     * @Param date
     * @return String
     **/
    public static String getTimeFormatText(long date) {
        if (date == 0) return "";
        date = date * 1000;
        long diff = new Date().getTime() - date;
        long r = 0;
        if (diff > year) { r = (diff / year); return r + "年前"; }
        if (diff > month) { r = (diff / month);return r + "月前"; }
        if (diff > day) { r = (diff / day);return r + "天前"; }
        if (diff > hour) { r = (diff / hour);return r + "小时前"; }
        if (diff > minute) { r = (diff / minute);return r + "分钟前"; }
        return "刚刚";
    }

    /**
     * @Description 计算两个日期之间相差的天数
     * @Date 2019/1/4 15:24
     * @param date1  时间1
     * @param date2  时间2
     * @return int
     **/
    public static int daysBetween(Date date1, Date date2) {
        boolean result = date1.before(date2);//对比时间的前后
        Date sdate,bdate;
        if(result==true){
            sdate = date2;bdate = date1;
        }else{
            sdate = date1;bdate = date2;
        }
        int days = (int) ((sdate.getTime() - bdate.getTime()) / (1000*3600*24));
        return days;

    }



    /**
     * @Description 获取分秒格式化字符串
     * @Date 2019/1/4 16:04
     * @param  time 时间
     * @return String
     **/
    public static String getFormatMiniteSecString(int time) {
        int second = time % 60;
        int minute = time / 60;
        int hour = 0;
        if (minute >= 60) {
            hour = minute / 60;
            minute = minute % 60;
        }
        String timeString = "";
        String secondString = "";
        String minuteString = "";
        String hourString = "";
        if (second < 10) {
            secondString = "0" + second;
        } else {
            secondString = second + "";
        }
        if (minute < 10) {
            minuteString = "0" + minute;
        } else {
            minuteString = minute + "";
        }
        if (hour < 10) {
            hourString = "0" + hour;
        } else {
            hourString = hour + "";
        }
        if (hour != 0) {
            timeString = hourString + ":" + minuteString + ":" + secondString;
        } else {
            timeString = minuteString + ":" + secondString;
        }
        return timeString;
    }

    /**
     * @Description 判断选择的日期是否是本周
     * @Date 2019/1/4 16:24
     * @param time
     * @return boolean
     **/
    public static boolean isThisWeek(long time)
    {
        Calendar calendar = Calendar.getInstance();
        int currentWeek = calendar.get(Calendar.WEEK_OF_YEAR);
        calendar.setTime(new Date(time));
        int paramWeek = calendar.get(Calendar.WEEK_OF_YEAR);
        if(paramWeek==currentWeek) return true;
        return false;
    }

    /**
     * @Description 判断选择的日期是否是今天
     * @Date 2019/1/4 16:24
     * @param time
     * @return boolean
     **/
    public static boolean isToday(long time) {
        return isThisTime(time,"yyyy-MM-dd");
    }

    /**
     * @Description 判断选择的日期是否是本月
     * @Date 2019/1/4 16:24
     * @param time
     * @return boolean
     **/
    public static boolean isThisMonth(long time) {
        return isThisTime(time,"yyyy-MM");
    }

    /**
     * @Description  处理时间对比的类
     * @Date 2019/1/4 16:24
     * @param time pattern
     * @param pattern
     * @return boolean
     **/
    private static boolean isThisTime(long time,String pattern) {
        Date date = new Date(time);
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String param = sdf.format(date);//参数时间
        String now = sdf.format(new Date());//当前时间
        if(param.equals(now)) return true;
        return false;
    }

    public static void main(String[] args) throws ParseException {
        String format = "HH:mm:ss";
        Date nowTime = new SimpleDateFormat(format).parse("09:28:00");
        Date startTime = new SimpleDateFormat(format).parse("09:27:00");
        Date endTime = new SimpleDateFormat(format).parse("09:27:59");
        System.out.println(isEffectiveDate(nowTime, startTime, endTime));
        System.out.println(getFormatMiniteSecString(123));
    }

    /**
     * @Description 判断当前时间是否在[startTime, endTime]区间，注意时间格式要一致
     * @Date 2019/1/4 16:42
     * @param nowTime 当前时间
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return boolean
     **/
    public static boolean isEffectiveDate(Date nowTime, Date startTime, Date endTime) {
        if (nowTime.getTime() == startTime.getTime() || nowTime.getTime() == endTime.getTime())
            return true;
        if (nowTime.after(startTime) && nowTime.before(endTime)) {
            return true;
        } else {
            return false;
        }
    }








}
