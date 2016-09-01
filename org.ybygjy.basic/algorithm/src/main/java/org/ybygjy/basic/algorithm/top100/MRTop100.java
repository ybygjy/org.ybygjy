package org.ybygjy.basic.algorithm.top100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 数据分块合并
 * @author WangYanCheng
 * @version 2016年9月1日
 */
public class MRTop100 {
    private int[] dataArr;
    public static final int SIZE = 100;
    /*存储数据分区*/
    private final Map<String, int[]> dataMap = new ConcurrentHashMap<String, int[]>();
    public MRTop100(int[] dataArr) {
        this.dataArr = dataArr;
    }
    public void doWork() {
        final ExecutorService executeService = Executors.newFixedThreadPool(5);
        int startPos = 0,endPos = 0, group = (int) (dataArr.length/SIZE);
        group = group + ((dataArr.length % SIZE) == 0 ? 0 : 1);
System.out.println("共" + group + "组");
        CountDownLatch countDownLatch = new CountDownLatch(group);
        for (int i = 0; i < group; i++) {
            startPos = i * SIZE;
            endPos = startPos + SIZE;
            endPos = (endPos > dataArr.length) ? dataArr.length : endPos;
            Worker worker = new Worker(countDownLatch, startPos, endPos, this.dataArr);
            executeService.submit(worker);
        }
        try {
            countDownLatch.await();
            mergeResult();
            executeService.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /**
     * 结果集合并
     */
    public void mergeResult() {
System.out.println("合并结果集");
        List<String> keyList = new ArrayList<String>(this.dataMap.keySet());
        int[] resultArr = new int[0];
        for (int index = 0; index < keyList.size(); index++) {
            int[] tmpObj = (int[]) this.dataMap.get(keyList.get(index));
            int[] tmpArr = new int[resultArr.length + tmpObj.length];
            System.arraycopy(resultArr, 0, tmpArr, 0, resultArr.length);
            System.arraycopy(tmpObj, 0, tmpArr, resultArr.length, tmpObj.length);
            resultArr = tmpArr;
        }
        Arrays.sort(resultArr);
        this.printData(resultArr);
    }
    /**
     * 工作线程
     * @author WangYanCheng
     * @version 2016年9月1日
     */
    class Worker implements Runnable {
        private int startPos, endPos;
        private int[] dataArr;
        private long costTime;
        private CountDownLatch countDownLatch;
        public Worker(CountDownLatch countDownLatch, int startPos, int endPos, int[] dataArr) {
            this.countDownLatch = countDownLatch;
            this.startPos = startPos;
            this.endPos = endPos;
            this.dataArr = dataArr;
        }
        public void run() {
            long startTime = System.currentTimeMillis();
            for (int i = startPos; i < this.endPos; i++) {
                for (int j = startPos; j < this.endPos; j++) {
                    if (this.dataArr[i] > this.dataArr[j]) {
                        int tmp = this.dataArr[i];
                        this.dataArr[i] = this.dataArr[j];
                        this.dataArr[j] = tmp;
                    }
                }
            }
            int[] tmpArr = new int[5];
            System.arraycopy(dataArr, 0, tmpArr, 0, tmpArr.length);
            dataMap.put(this.startPos + "_" + this.endPos, tmpArr);
            this.costTime = System.currentTimeMillis() - startTime;
            System.out.println(this.toString());
            this.countDownLatch.countDown();
        }
        public String toString() {
            StringBuilder sbud = new StringBuilder();
            sbud.append("{").append(Thread.currentThread().getName()).append(" ").append(this.startPos).append("-").append(this.endPos).append("#").append("耗时：").append(this.costTime/1000).append("秒").append("}");
            return sbud.toString();
        }
    }
    public String printData(int[] arr) {
        StringBuilder sbud = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            sbud.append(arr[i]).append(" ");
            if (i != 0 && i % 20 == 0) {
                sbud.append('\n');
            }
        }
System.out.println(sbud);
        return sbud.toString();
    }
    public String printData() {
        return this.printData(this.dataArr);
    }
    public static void main(String[] args) {
        final int[] dataArr = new int[(int) Math.pow(MRTop100.SIZE, 2)];
        long startTime = System.currentTimeMillis();
        Random random = new Random();
        for (int i = 0; i < dataArr.length; i++) {
            dataArr[i] = (int)(random.nextInt(MRTop100.SIZE));
        }
        System.out.println("初始化完成，耗时：" + (System.currentTimeMillis() - startTime) / 1000 + "秒");
        MRTop100 mrtopInst = new MRTop100(dataArr);
        mrtopInst.doWork();
        while (Thread.activeCount() > 1) {
            Thread.yield();
        }
    }
}
