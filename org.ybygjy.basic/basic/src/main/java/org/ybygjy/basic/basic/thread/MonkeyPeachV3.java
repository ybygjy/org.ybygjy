package org.ybygjy.basic.basic.thread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 猴子吃桃V3
 * @author leye
 * @version 2018-02-25
 */
public class MonkeyPeachV3 {
    static class Monkey extends Thread {
        private Semaphore semaphore;
        private int perNums;
        public Monkey(String monkeyName, int perNums, Semaphore semaphore) {
            this.setName(monkeyName);
            this.perNums = perNums;
            this.semaphore = semaphore;
        }
        public void run() {
            while (true) {
                try {
                    if (semaphore.tryAcquire(perNums, 100, TimeUnit.MILLISECONDS)) {
                        System.out.println(getName() + ", eat: " + perNums + "," + semaphore.availablePermits());
                    } else {
                        System.out.println(getName() + ", finished.");
                        break;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(9);
        new Monkey("MonkeyA", 2, semaphore).start();
        new Monkey("MonkeyB", 3, semaphore).start();
        new Monkey("MonkeyC", 2, semaphore).start();
        new Monkey("MonkeyD", 3, semaphore).start();
        new Monkey("MonkeyE", 2, semaphore).start();
        new Monkey("MonkeyF", 3, semaphore).start();
    }
}
