package org.ybygjy.spring.c1.aop;

/**
 * Created by leye on 2017/11/13.
 */
public class LoggerHandler {
    public void beforeLogger() {
        System.out.println("before logger:" + System.currentTimeMillis());
    }
    public void afterLogger() {
        System.out.println("after logger:" + System.currentTimeMillis());
    }
}
