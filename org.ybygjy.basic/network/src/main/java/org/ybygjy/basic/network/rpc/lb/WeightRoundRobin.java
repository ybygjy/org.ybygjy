package org.ybygjy.basic.network.rpc.lb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 加权轮询
 * @author WangYanCheng
 * @version 2016年8月31日
 */
public class WeightRoundRobin extends AbstractLoadBalance {
    /** 索引值*/
    private final AtomicInteger index = new AtomicInteger();
    private final CountInnerClass countInnerClazz = new CountInnerClass();
    @Override
    public String getServerAddr() {
        Map<String, Integer> copyMap = new HashMap<String, Integer>(serverMap);
        List<String> keylists = new ArrayList<String>();
        for (Iterator<String> iterator = copyMap.keySet().iterator(); iterator.hasNext();) {
            String key = iterator.next();
            Integer weight = copyMap.get(key);
            for (int i = 0; i < weight; i++) {
                keylists.add(key);
            }
        }
        int size = keylists.size();
        this.countInnerClazz.setTotalCount(size);
        if (this.index.get() >= size) {
            this.index.set(0);
        }
        return keylists.get(this.index.getAndIncrement());
    }
    static class CountInnerClass{
        private Map<String, AtomicInteger> countMap = new ConcurrentHashMap<String, AtomicInteger>();
        private volatile int totalCount = 1;
        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }
        public synchronized void addKey(String key) {
            if (this.countMap.containsKey(key)) {
                countMap.get(key).incrementAndGet();
            } else {
                this.countMap.put(key, new AtomicInteger(1));
            }
        }
        public void printReports() {
            int count = 0;
            for (Iterator<Map.Entry<String, AtomicInteger>> iterator = this.countMap.entrySet().iterator(); iterator.hasNext();) {
                Map.Entry<String, AtomicInteger> entry = iterator.next();
                int ratioOfTotal = (int) ((entry.getValue().floatValue() / this.totalCount) * 100);
                System.out.println(entry.getKey() + "#" + ratioOfTotal + "%#" + this.totalCount);
                count += entry.getValue().intValue();
            }
            System.out.println(count);
        }
    }
    public static void main(String[] args) {
        final WeightRoundRobin weightRoundRobin = new WeightRoundRobin();
        final ExecutorService executorService = Executors.newFixedThreadPool(10);
        final CountDownLatch countDownLatch = new CountDownLatch(100);
        for (int i = 0; i < 100; i++) {
            executorService.submit(new Runnable() {
                public void run() {
                    String serverAddr = (weightRoundRobin.getServerAddr());
                    weightRoundRobin.countInnerClazz.addKey(serverAddr);
                    countDownLatch.countDown();
                }
            });
        }
        try {
            countDownLatch.await();
            executorService.shutdown();
            weightRoundRobin.countInnerClazz.printReports();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
