package org.ybygjy.basic.basic.thread;

/**
 * 线程间通信
 * <p>1.wait\notify\notifyAll应用</p>
 * <p>2.构造多线程间通信场景实践</p>
 * @author leye
 * @version 2018-02-03
 */
public class SyncBlockAndNotify {
    private volatile int totalThreads;
    private volatile int currentThreads;
    private long currTime;
    public SyncBlockAndNotify(int totalThreads) {
        this.totalThreads = totalThreads;
        this.currentThreads = 0;
        currTime = 0;
    }
    public static void main(String[] args) {
        new SyncBlockAndNotify(10).doWork();
    }
    public void doWork() {
        currTime = System.currentTimeMillis();
        for (int i = 0; i < totalThreads; i++) {
            new InnerThread("T_" + i, this).start();
        }
    }
    private synchronized void waitForAll() {
        currentThreads++;
        if (currentThreads < totalThreads) {
            try {
                //这里的对象句柄是该对象实例，其实就是多线程通过共享变量的wait/notify进行状态消息传递，这里的场景就是等待
                //多线程各自的工作全部完成，统计耗时
                this.wait();
                if (currentThreads < totalThreads) {
                    currentThreads = Integer.MAX_VALUE;
                    System.out.println(Thread.currentThread().getName() + ",thread time consuming:" + ((System.currentTimeMillis() - currTime)));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            currentThreads = 0;
            this.notifyAll();
        }
    }
    static class InnerThread extends Thread {
        private SyncBlockAndNotify shareObj;
        public InnerThread(String threadName, SyncBlockAndNotify shareObj) {
            super(threadName);
            this.shareObj = shareObj;
        }
        public void run() {
            for (int i = 0; i < 100; i++) {
                try {
                    System.out.println(getName() + ":" + System.currentTimeMillis());
                    sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            shareObj.waitForAll();
        }
    }
}
