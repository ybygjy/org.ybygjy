package org.ybygjy.basic.basic.thread;

/**
 * 通过当前线程上下文堆栈信息得到调用者信息
 * Created by leye on 2017/1/19.
 */
public class ThreadStacktraceElements {
    private User user = new User();
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                new ThreadStacktraceElements().user.doWork();
            }
        }).start();
    }
    class User {
        String userName;
        public void doWork() {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            for (StackTraceElement stackTraceElement : stackTraceElements) {
                System.out.println(stackTraceElement.getClassName() + "#" + stackTraceElement.getMethodName());
            }
        }
    }
}
