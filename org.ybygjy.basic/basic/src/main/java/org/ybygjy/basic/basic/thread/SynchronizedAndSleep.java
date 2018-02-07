package org.ybygjy.basic.basic.thread;

/**
 * Thread.sleep是不会出让锁资源的
 * @author leye
 * @version 2018-02-06
 */
public class SynchronizedAndSleep {
    public static void main(String[] args) {
        Object lock = new Object();
        new InnerThread(lock).start();
        new InnerThread(lock).start();
    }
    static class InnerThread extends Thread {
        private Object shareObj = null;
        public InnerThread(Object shareObj) {
            this.shareObj = shareObj;
        }
        public void run() {
            while (true) {
                synchronized (shareObj) {
                    System.out.println(getName() + "->do work...");
                    System.out.println(getName() + "->do done...");
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
