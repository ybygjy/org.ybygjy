package org.ybygjy.spring.orderservice.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 封装字符串工具类
 * @author WangYanCheng
 * @version 2016年10月22日
 */
public class StringUtils {
    private static class InnerClass {
        public static final StringUtils strUtilsInst = new StringUtils();
    }
    /**
     * private constructor
     */
    private StringUtils() {
    }
    /**
     * 取实例
     * @return {@link StringUtils}
     */
    public static StringUtils getInstance() {
        return StringUtils.InnerClass.strUtilsInst;
    }
    /**
     * 取时间戳+随机6位组成的字符串
     * @return rtnStr
     */
    public String getTSRandomNo() {
        StringBuilder sbud = new StringBuilder();
        sbud.append(DateUtils.getInstance().getDateByPattern(new Date(), DateUtils.DATETIME_LOOSE_FMT));
        sbud.append((int) ((ThreadLocalRandom.current().nextDouble() * 9 + 1) * 100000));
        return sbud.toString();
    }
    public static void main(String[] args) {
//        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(5, 10, 10, TimeUnit.MINUTES, new ArrayBlockingQueue<Runnable>(10));
//        for (int i = 0; i < 20; i++) {
//            threadPool.submit(new Runnable() {
//                public void run() {
//                    System.out.println(Thread.currentThread().getName() + "#" + StringUtils.getInstance().getTSRandomNo());
//                }
//            });
//        }
//        threadPool.shutdown();
        int i = Integer.SIZE - 3;
        int tmpV = -1 << i;
        System.out.println(Integer.toBinaryString(-1));
        System.out.println(Integer.toBinaryString(tmpV));
        for (i = 1; i <= 10; i++) {
            String tmpValue = Integer.toBinaryString(1 << i);
            System.out.println(tmpValue + "\t\t》》" + Integer.parseInt(tmpValue, 2));
        }
    }
}
