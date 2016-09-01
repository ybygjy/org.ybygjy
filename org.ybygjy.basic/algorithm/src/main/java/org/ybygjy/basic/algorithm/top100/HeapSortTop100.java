package org.ybygjy.basic.algorithm.top100;

import java.util.Arrays;
import java.util.Random;

/**
 * 堆排序Top 100
 * @author WangYanCheng
 * @version 2016年9月1日
 */
public class HeapSortTop100 {
    private int[] dataArr = new int[1000];
    public HeapSortTop100() {
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            this.dataArr[i] = (int)(random.nextDouble() * 1000);
            //this.dataArr[i] = i;
        }
    }
    public int[] doWork() {
        int[] rtnArr = new int[10];
        System.arraycopy(this.dataArr, 0, rtnArr, 0, rtnArr.length);
        int pos = rtnArr.length;
        Arrays.sort(rtnArr);
        for (int i = pos; i < this.dataArr.length; i++) {
            if (this.dataArr[i] < rtnArr[0]) {
                continue;
            }
            rtnArr[0] = this.dataArr[i];
            Arrays.sort(rtnArr);
        }
        return rtnArr;
    }
    public static void main(String[] args) {
        HeapSortTop100 heapSortTop100 = new HeapSortTop100();
        int[] dataArr = heapSortTop100.doWork();
        System.out.println(Arrays.toString(dataArr));
    }
}
