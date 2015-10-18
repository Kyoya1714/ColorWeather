package com.hao.colorweather.utils;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 * ==================================================
 * 版    权：
 * <p/>
 * 作    者：钟旺浩
 * <p/>
 * 版    本：1.0
 * <p/>
 * 日    期：2015/9/28 14:16
 * ===================================================
 */
public class MyTextUtils {
    /**
     * 获取实时时间
     * @param time
     * @return
     */
    public static String splitTime(String time){
        int index = time.indexOf(" ");
        return time.substring(index,time.length()-3);
    }
    /**
     * 获取发布时间
     * @param time
     * @return
     */
    public static String splitFaBuTime(String time){
        int index = time.indexOf(" ");
        return time.substring(index,time.length());
    }
    /**
     * 获取周几
     * @param dt
     * @param i：当天请设置为0 后一天1 以此类推
     * @return
     */
    public static String getWeekOfDate(Date dt,int i) {
        String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六","周日", "周一", "周二", "周三", "周四", "周五", "周六","周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) +i-1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    /**
     * 获取今天后的某天日期
     * @param i：今天后的第几天
     * @return
     */
    public static String getNextDate(int i){

            //获取当前日期
            Date date = new Date();
            SimpleDateFormat sf = new SimpleDateFormat("MM/dd");
            String nowDate = sf.format(date);
            System.out.println(nowDate);
            //通过日历获取某天日期
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            cal.add(Calendar.DAY_OF_YEAR, +i);
            return   sf.format(cal.getTime());
        }

    /**
     * 获取当天年月日
     * @return
     */
    public static String getNowDate(){
        //获取当前日期
        Date date = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        return sf.format(date);
    }
    /**
     * 切割经纬度数字
     * @param str
     * @return
     */
    public static String splitJingWei(String str){
        int index = str.indexOf(".");
        return str.substring(0,index+4);
    }
}
