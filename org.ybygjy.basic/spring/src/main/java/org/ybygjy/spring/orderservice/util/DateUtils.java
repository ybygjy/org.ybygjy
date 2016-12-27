package org.ybygjy.spring.orderservice.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期相关工具类
 * @author WangYanCheng
 * @version 2016年10月22日
 */
public class DateUtils {
    /**常用日期格式 yyyy-MM-dd*/
    public static final String DATE_STRICT_FMT = "yyyy-MM-dd";
    /**常用日期格式 yyyyMMdd*/
    public static final String DATE_LOOSE_FMT = "yyyyMMdd";
    /**常用日期格式 yyyy-MM-dd HH:mm:ss*/
    public static final String DATETIME_STRICT_FMT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATETIME_LOOSE_FMT = "yyyyMMddHHmmss";
    public static final DateFormat DATE_STRICT_FORMAT = new SimpleDateFormat(DATE_STRICT_FMT);
    public static final DateFormat DATE_LOOSE_FORMAT = new SimpleDateFormat(DATE_LOOSE_FMT);
    public static final DateFormat DATETIME_FORMAT = new SimpleDateFormat(DATETIME_STRICT_FMT);
    public static final DateFormat DATETIME_LOOSE_FORMAT = new SimpleDateFormat(DATETIME_LOOSE_FMT);
    /**内部静态类 加载初始化成员变量期间生成并持有单例对象的引用*/
    public static class InnerClass {
        public static final DateUtils dateUtilsInst = new DateUtils();
    }
    /**
     * private Construction
     */
    private DateUtils() {
    }
    /**
     * 取实例对象
     * @return {@link DateUtils}
     */
    public static DateUtils getInstance() {
        return InnerClass.dateUtilsInst;
    }
    /**
     * 返回利用给定Pattern格式化后的字符串
     * @param date {@link Date}
     * @param pattern {@link SimpleDateFormat}
     * @return rtnStr/null
     */
    public String getDateByPattern(Date date, String pattern) {
        try {
            DateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
