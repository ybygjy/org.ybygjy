package org.ybygjy.basic.network.rpc.lb;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 随机
 * @author WangYanCheng
 * @version 2016年8月30日
 */
public class RandomStrategy extends AbstractLoadBalance {
    private static final Random random = new Random();
    @Override
    public String getServerAddr() {
        List<String> keyList = new ArrayList<String>(serverMap.keySet());
        int index = random.nextInt(keyList.size());
        return keyList.get(index);
    }
    public static void main(String[] args) {
        final AbstractLoadBalance lbImpl = new RandomStrategy();
        final ExecutorService executorService = Executors.newFixedThreadPool(5);
        final AtomicInteger count = new AtomicInteger();
        for (int i = 0; i < 20; i++) {
            executorService.execute(new Runnable() {
                public void run() {
                    System.out.println(count.incrementAndGet() + "#" + Thread.currentThread().getName() + "#" + lbImpl.getServerAddr());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        while (count.get() != 20) {
            Thread.yield();
        }
        executorService.shutdown();
    }
}
