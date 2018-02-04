package org.ybygjy.basic.basic.thread;

/**
 * @author leye
 * @version 2018-02-02
 */
public class ThreadJoinTest {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            ChildThread childThread = new ChildThread();
            childThread.start();
        }
        System.out.println("HelloWorld");
//        try {
//            Thread.currentThread().join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
    static class ChildThread extends Thread {
        public void run() {
            try {
                for (int i = 0; i < 100; i++) {
                    sleep(10);
                    System.out.println(getName() + "#" + i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
