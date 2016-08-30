package org.ybygjy.basic.network.rpc.lb;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 轮循
 * @author WangYanCheng
 * @version 2016年8月30日
 */
public class RoundRobin extends AbstractLoadBalance {
    private static Object lock = new Object();
    private static volatile int position = 0;

    public String getServerAddr() {
        Set<String> keySet = serverMap.keySet();
        List<String> keyList = new ArrayList<String>(keySet);
        String serverAddr = null;
        synchronized (lock) {
            if (position >= keyList.size()) {
                position = 0;
            }
            serverAddr = keyList.get(position);
            position++;
        }
        return serverAddr;
    }

    public static void main(String[] args) {
        final RoundRobin roundRobin = new RoundRobin();
        final ExecutorService executorService = Executors.newFixedThreadPool(5);
        int threadCount = 50;
        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        for (int i = 0; i < 50; i++) {
            executorService.submit(new Runnable() {
                public void run() {
                    System.out.println(Thread.currentThread().getName() + "#" + roundRobin.getServerAddr());
                    countDownLatch.countDown();
                }
            });
        }
        try {
            countDownLatch.await();
            executorService.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
