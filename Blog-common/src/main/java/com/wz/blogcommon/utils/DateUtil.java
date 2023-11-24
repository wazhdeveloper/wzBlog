package com.wz.blogcommon.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    private static final Calendar calendar = Calendar.getInstance();

    public static String getYear() {
        return String.valueOf(calendar.get(Calendar.YEAR));
    }

    public static String getMonth() {
        return String.valueOf(calendar.get(Calendar.MONTH) + 1);
    }

    public static String getDay() {
        return String.valueOf(calendar.get(Calendar.DATE));
    }

    /**
     * @return 年-月-日 时:分:秒
     */
    public static Date getNow() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String nowDate = now.format(dateTimeFormatter);
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return format.parse(nowDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}