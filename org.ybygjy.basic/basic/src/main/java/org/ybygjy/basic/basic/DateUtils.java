package org.ybygjy.basic.basic;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by leye on 2017/11/27.
 */
public class DateUtils {
    public static Date convertLongToDate(Long val) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.setTimeInMillis(val);
        return calendar.getTime();
    }

    public static void main(String[] args) {
        Long tmpVal = 1494517759000L;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(simpleDateFormat.format(DateUtils.convertLongToDate(tmpVal)));
    }
}
