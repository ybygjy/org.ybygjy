package org.ybygjy.basic.basic.thread;

/**
 * 实践验证线程分配内存溢出
 * Created by leye on 2017/4/18.
 */
public class ThreadAllowMemory {
    public void doWork() {
        int x = 0;
        try {
            while (true) {
                new InnerThread(x++).start();
            }
        } catch(Exception e) {
            e.printStackTrace();
        } catch (Throwable e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
    class InnerThread extends Thread {
        private InnerThread(int x) {
            this.setName("Thread#" + x);
        }
        public void run() {
            while (true) {
                //System.out.println(getName());
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        new ThreadAllowMemory().doWork();
        //Thread.currentThread().join();
    }
}
