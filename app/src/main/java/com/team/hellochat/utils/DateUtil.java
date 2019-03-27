package com.team.hellochat.utils;

import java.net.URL;
import java.net.URLConnection;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Sweven on 2019/3/22.
 * Email:sweventears@Foxmail.com
 */
public class DateUtil {

    /**
     * 需要再 OnCreate() 方法中添加以下代码来解决
     * android.os.NetworkOnMainThreadException 异常
     * StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
     * StrictMode.setThreadPolicy(policy);
     *
     * @return date
     */
    public static Date getNetTime() {

        String webUrl = "http://www.ntsc.ac.cn";//中国科学院国家授时中心
        try {
            URL url = new URL(webUrl);
            URLConnection uc = url.openConnection();
            uc.setReadTimeout(3000);
            uc.setConnectTimeout(3000);
            uc.connect();
            long correctTime = uc.getDate();
            return new Date(correctTime);
        } catch (Exception e) {
            e.getStackTrace();
            return null;
        }
    }

    /**
     * @param year  year
     * @param month month
     * @return days of month
     */
    public static int getDaysOfMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DATE, 1);
        calendar.roll(Calendar.DATE, -1);
        return calendar.get(Calendar.DATE);
    }

    /**
     * @param year  year
     * @param month month
     * @return Get the day of the week for the 1st of the month
     */
    public static int getWeekdayOfMonthFirstDay(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DATE, 22);
        int day = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        return day == 0 ? 7 : day;
    }

    public static String getYearMonth() {
        Date date = DateUtil.getNetTime();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        return year + (month < 10 ? "0" + month : month + "");
    }
}
