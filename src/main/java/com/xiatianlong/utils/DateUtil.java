package com.xiatianlong.utils;

import org.apache.commons.lang.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 * Created by xiatianlong on 2017/5/28.
 */
public class DateUtil {

    public final static String defaultDatePattern = "yyyy-MM-dd HH:mm:ss";
    public final static String dateformatter_yyyy_MM_dd = "yyyy-MM-dd";
    public final static String dateformatter_YYMM = "yyyyMM";
    public final static String TIME_FORMATTER_HHMM = "yyyy-MM-dd HH:mm";
    public final static String TIME_FORMATTER_HHMMSS = "yyyy年MM月dd日 HH:mm:ss";
    public final static String TIME_FORMATTER_YYMM = "yyyy年MM月";
    public final static String TIME_FORMATTER_MMDD = "MM月dd日";
    public final static String TIME_FORMATTER_YYMMDD = "yyyy年MM月dd日";

    /**
     * 将日期对象转换成制定格式字符串
     *
     * @param date        日期对象
     * @param datePattern 日期格式
     * @return String
     */
    public static String getFormatString(Date date, String datePattern) {
        if (date != null) {
            SimpleDateFormat sd = new SimpleDateFormat(datePattern);
            return sd.format(date);
        } else {
            return null;
        }
    }

    /**
     * 使用预设格式将字符串转为Date
     */
    public static Date parse(String strDate) {
        return StringUtils.isBlank(strDate) ? null : parse(strDate, dateformatter_yyyy_MM_dd);
    }

    /**
     * 使用参数Format将字符串转为Date
     */
    public static Date parse(String strDate, String pattern) {
        try {
            return StringUtils.isBlank(strDate) ? null : new SimpleDateFormat(pattern).parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取当前月的第一天
     * @return  当前月的第一天
     */
    public static String currentMonthFirstDay(){
        SimpleDateFormat formatter = new SimpleDateFormat(dateformatter_yyyy_MM_dd);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
        return formatter.format(calendar.getTime());
    }

    /**
     * 获取传入年月的月的第一天
     * @param date  日期， 限制yyyyMM类型
     * @return  当前月的第一天
     */
    public static String getDateMonthFirstDay(String date){

        SimpleDateFormat formatter = new SimpleDateFormat(dateformatter_yyyy_MM_dd);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, Integer.valueOf(date.substring(0,4)));
        calendar.set(Calendar.MONTH, Integer.valueOf(date.substring(date.length()-2,date.length()))-1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
        return formatter.format(calendar.getTime());
    }

}
