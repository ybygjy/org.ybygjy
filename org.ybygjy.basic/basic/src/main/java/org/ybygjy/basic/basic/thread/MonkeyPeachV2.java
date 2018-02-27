package org.ybygjy.basic.basic.thread;

import java.util.concurrent.CountDownLatch;

/**
 * 这个类描述了多线程共享变量引用地址被修改出现并发问题
 * @author leye
 * @version 2018-02-06
 */
public class MonkeyPeachV2 implements Runnable {
    private static Integer peachNums = 9;
    private int eatNums;
    private CountDownLatch countDownLatch;
    public MonkeyPeachV2(int eatNums, CountDownLatch countDownLatch) {
        this.eatNums = eatNums;
        this.countDownLatch = countDownLatch;
    }
    @Override
    public void run() {
        while(this.eatPeach()) {
        }
    }
    private boolean eatPeach() {
        try {
            this.countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //锁因为内容逻辑被改变了
        synchronized (peachNums) {
            String snapshotVal = peachNums + "," + eatNums;
            int currNums = peachNums - eatNums;
            if (currNums >= 0) {
                try {
                    Thread.currentThread().sleep((long) (Math.random() * 100));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                peachNums = currNums;
                System.out.println(Thread.currentThread().getName() + "," + snapshotVal + ",苹果数量:" + peachNums + "," + eatNums);
                return true;
            }
            return false;
        }
    }
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        new Thread(new MonkeyPeachV2(1, countDownLatch)).start();
        new Thread(new MonkeyPeachV2(2, countDownLatch)).start();
        new Thread(new MonkeyPeachV2(3, countDownLatch)).start();
        new Thread(new MonkeyPeachV2(4, countDownLatch)).start();
        new Thread(new MonkeyPeachV2(7, countDownLatch)).start();
        countDownLatch.countDown();
    }
}