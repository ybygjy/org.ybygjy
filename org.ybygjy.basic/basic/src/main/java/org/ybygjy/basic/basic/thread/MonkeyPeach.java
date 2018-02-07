package org.ybygjy.basic.basic.thread;

import java.util.concurrent.atomic.AtomicInteger;

public class MonkeyPeach {
    public static void main(String[] args) {
        AtomicInteger peachNums = new AtomicInteger(9);
        new Thread(new Monkey(2, peachNums)).start();
        new Thread(new Monkey(3, peachNums)).start();
    }
    static class Monkey implements Runnable {
        private int perEat;
        private final AtomicInteger peachNums;
        public Monkey(int perEat, AtomicInteger peachNums) {
            this.perEat = perEat;
            this.peachNums = peachNums;
        }
        public void run() {
            while (true) {
                synchronized (peachNums) {
                    if (peachNums.get() <= perEat) {
                        break;
                    }
                    peachNums.set(peachNums.get() - perEat);
                    System.out.println(Thread.currentThread().getName() + "=>" + perEat + ":" + peachNums.get());
                }
                try {
                    Thread.currentThread().sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
