package org.ybygjy.spring.web;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * LoginAction instance for each and every http request.
 * @author WangYanCheng
 * @version 2016年9月27日
 */
public class LoginAction {
    private String path;
    private final static AtomicInteger counts = new AtomicInteger();
    public LoginAction() {
        int updatedValue = counts.incrementAndGet();
        System.out.println(LoginAction.class.getName() + "#" + updatedValue);
    }
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    public void doWork() {
        System.out.println(this.getPath() + ">>>>>>" + counts.get());
    }
}
