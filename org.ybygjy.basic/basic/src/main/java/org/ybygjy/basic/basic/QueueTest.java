package org.ybygjy.basic.basic;

import jdk.nashorn.internal.ir.Block;

import java.util.*;
import java.util.concurrent.*;

/**
 * Created by leye on 2017/4/28.
 */
public class QueueTest {
    public static void main(String[] args) throws InterruptedException {
        Queue<String> queue = new LinkedList<String>();
        //element和peek的共性是获取head元素并删除，不同是element在队列为空时会抛出异常而peek会返回null
        //remote和poll的共性是获取head元素不删除，不同是remote在队列为空会抛出异常而pool只会返回null
        //add和offer的共性是队尾增加一个元素，add在队列满时会抛出异常而offer只会返回false
//        BlockingQueue<String> blockingQueue = new LinkedBlockingDeque<String>(10);
//        new QueueTest().providerService(blockingQueue);
//        new QueueTest().consumerService(blockingQueue);
        Random random = new Random();
        DelayQueue delayQueue = new DelayQueue();
        for (int i = 0; i < 5; i++) {
            delayQueue.add(new NanoDelay(random.nextInt(1000)));
        }
        long last = 0;
        for (int i = 0; i < 5; i++) {
            NanoDelay delay = (NanoDelay) (delayQueue.take());
            long tt = delay.getTriggerTime();
            System.out.println("Trigger Time: " + tt);
            if (i != 0) {
                System.out.println("Delta: " + (tt - last));
            }
            last = tt;
        }
//        try {
//            Thread.currentThread().join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
    public void providerService(final BlockingQueue<String> blockingQueue) {
        int threadNum = 5;
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        blockingQueue.put(Thread.currentThread().getName() + "_" + Math.random());
                        Thread.currentThread().sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
    }
    public void consumerService(final BlockingQueue<String> blockingQueue) {
        int threadNum = 10;
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    try {
                        String result = blockingQueue.take();
                        Thread.currentThread().sleep(500);
                        System.out.println(Thread.currentThread().getName() + "_" + result);
                    } catch(InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
    }
    static class NanoDelay implements Delayed {
        long trigger;
        NanoDelay(long i) {
            trigger = System.nanoTime() + i;
        }
        public boolean equals(Object obj) {
            return ((NanoDelay) obj).trigger == trigger;
        }
        public boolean equals(NanoDelay obj) {
            return ((NanoDelay) obj).trigger == trigger;
        }
        public long getDelay(TimeUnit unit) {
            long n = trigger - System.nanoTime();
            System.out.println(n);
            return unit.convert(Math.abs(n), TimeUnit.NANOSECONDS);
        }
        public long getTriggerTime() {
            return trigger;
        }
        public String toString() {
            return String.valueOf(trigger);
        }

        @Override
        public int compareTo(Delayed o) {
            long i = trigger;
            long j = ((NanoDelay) o).trigger;
            return i == j ? 0 : i > j ? 1 : -1;
        }
    }
}
