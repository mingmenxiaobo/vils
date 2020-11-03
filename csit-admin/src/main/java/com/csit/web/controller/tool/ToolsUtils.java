package com.csit.web.controller.tool;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ToolsUtils {
    private static DateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /**********获取日期块**************/
    /**
     * 获取当前时间起始时间 字符串
     * @param dt 时间
     * @return 起始时间 字符串
     */
    public static String getTodayStartTimeToString(Date dt) {
        Calendar cale = Calendar.getInstance();
        cale.setTime(dt);
        cale.set(Calendar.HOUR_OF_DAY, 0);
        cale.set(Calendar.MINUTE, 0);
        cale.set(Calendar.SECOND, 0);
        cale.set(Calendar.MILLISECOND, 0);
        return df.format(cale.getTime());
    }
    public static Date getTodayStartTime(Date dt) {
        Calendar cale = Calendar.getInstance();
        cale.setTime(dt);
        cale.set(Calendar.HOUR_OF_DAY, 0);
        cale.set(Calendar.MINUTE, 0);
        cale.set(Calendar.SECOND, 0);
        cale.set(Calendar.MILLISECOND, 0);
        System.out.println(df.format(cale.getTime()));
        return cale.getTime();
    }

    /**
     * 获取当前天结束时间
     * @param dt 时间
     * @return 结束时间
     */
    public static Date getTodayEndTime(Date dt) {
        Calendar cale = Calendar.getInstance();
        cale.setTime(dt);
        cale.set(Calendar.HOUR_OF_DAY, 23);
        cale.set(Calendar.MINUTE, 59);
        cale.set(Calendar.SECOND, 59);
        cale.set(Calendar.MILLISECOND, 999);
        System.out.println(df.format(cale.getTime()));
        return cale.getTime();
    }

    /**
     * 当月第一天
     * @param dt
     * @return
     */
    public static Date getMonthFirst(Date dt){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dt);
        int year=calendar.get(Calendar.YEAR);
        int month=calendar.get(Calendar.MONTH);
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        Date currYearFirst = calendar.getTime();
        System.out.println(df.format(currYearFirst));
        return currYearFirst;
    }

    /**
     * 当月最后一天
     * @param dt
     * @return
     */
    public static Date getMonthLast(Date dt){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dt);
        int year=calendar.get(Calendar.YEAR);
        int month=calendar.get(Calendar.MONTH);
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONDAY, month);
        calendar.roll(Calendar.DAY_OF_MONTH, -1);
        Date currYearLast = calendar.getTime();
        System.out.println(df.format(currYearLast));
        return currYearLast;
    }

    /**
     * 获取某年第一天日期
     * @param dt 年份
     * @return Date
     */
    public static Date getYearFirst(Date dt){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dt);
        int year=calendar.get(Calendar.YEAR);
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        Date currYearFirst = calendar.getTime();
        System.out.println(df.format(currYearFirst));
        return currYearFirst;
    }


    /**
     * 获取某年最后一天日期
     * @param dt 年份
     * @return Date
     */
    public static Date getYearLast(Date dt){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dt);
        int year=calendar.get(Calendar.YEAR);
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        Date currYearLast = calendar.getTime();
        System.out.println(df.format(currYearLast));
        return currYearLast;
    }
}
