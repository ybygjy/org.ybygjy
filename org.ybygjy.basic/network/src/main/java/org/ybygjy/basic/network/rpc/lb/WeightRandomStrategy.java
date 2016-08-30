package org.ybygjy.basic.network.rpc.lb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 加权随机
 * @author WangYanCheng
 * @version 2016年8月31日
 */
public class WeightRandomStrategy extends AbstractLoadBalance {
    private static final Random random = new Random();
    @Override
    public String getServerAddr() {
        HashMap<String, Integer> dataMap = new HashMap<String, Integer>(serverMap);
        List<String> keysList = new ArrayList<String>();
        for (Iterator<Map.Entry<String, Integer>> iterator = dataMap.entrySet().iterator(); iterator.hasNext();) {
            Map.Entry<String, Integer> entry = iterator.next();
            for (int i = 0; i < entry.getValue(); i++) {
                keysList.add(entry.getKey());
            }
        }
        int index = random.nextInt(keysList.size());
        return keysList.get(index);
    }
    public static void main(String[] args) {
        final WeightRandomStrategy weightRandomStrategy = new WeightRandomStrategy();
        final ExecutorService executorService = Executors.newFixedThreadPool(10);
        final CountDownLatch countDownLatch = new CountDownLatch(100);
        for(int i = 0; i < 100; i++) {
            executorService.execute(new Runnable() {
                public void run() {
                    System.out.println(weightRandomStrategy.getServerAddr());
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
