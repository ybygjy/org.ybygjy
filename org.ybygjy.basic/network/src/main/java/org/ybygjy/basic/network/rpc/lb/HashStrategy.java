package org.ybygjy.basic.network.rpc.lb;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 源地址HASH
 * @author WangYanCheng
 * @version 2016年8月30日
 */
public class HashStrategy extends AbstractLoadBalance {
    private String ipAddr;

    public HashStrategy(String ipAddr) {
        this.ipAddr = ipAddr;
    }

    @Override
    public String getServerAddr() {
        List<String> serviceList = new ArrayList<String>(serverMap.keySet());
        int index = ipAddr.hashCode() % serviceList.size();
        return serviceList.get(index);
    }

    public static void main(String[] args) {
        final String[] ipAddrs = new String[] { "192.168.10.1", "192.168.190.2", "115.182.120.200", "115.182.120.102", "115.182.121.253" };
        final ExecutorService executorService = Executors.newFixedThreadPool(10);
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(10, new Runnable() {
            public void run() {
                executorService.shutdown();
            }
        });
        for (int i = 0; i < 10; i++) {
            executorService.execute(new Runnable() {
                public void run() {
                    for (int j = 0; j < ipAddrs.length; j++) {
                        String ipAddr = ipAddrs[j];
                        System.out.println(ipAddr + "#" + new HashStrategy(ipAddr).getServerAddr());
                    }
                    try {
                        int arrivalIndex = cyclicBarrier.await();
                        System.out.println(Thread.currentThread().getName() + "# arrival index:" + arrivalIndex);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
