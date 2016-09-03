package org.ybygjy.basic.algorithm.sort;

import java.util.Arrays;

/**
 * 排序
 * @author WangYanCheng
 * @version 2016年9月3日
 */
public class Sort {
    /**
     * 插入排序
     */
    public void insertionSort() {
        int[] dataArr = {1, 3, 5, 7, 9, 2, 4, 6, 8, 0};
        int in = 0;
        int out = 0;
        for (out = 1; out < dataArr.length; out++) {
            int tmp = dataArr[out];
            in = out;
            while (in > 0 && dataArr[in - 1] >= tmp) {
                dataArr[in] = dataArr[in - 1];
                --in;
            }
            dataArr[in] = tmp;
            System.out.println(Arrays.toString(dataArr));
        }
        System.out.println();
        System.out.println(Arrays.toString(dataArr));
    }
    /**
     * 选择排序
     */
    public void selectSort() {
        int[] dataArr = {1, 3, 5, 7, 9, 2, 4, 6, 8, 0};
        int out = 0;
        int in = 0;
        int min = 0;
        for (out = 0; out < dataArr.length; out++) {
            min = out;
            for (in = out + 1; in < dataArr.length; in ++) {
                if (dataArr[in] < dataArr[min]) {
                    min = in;
                }
            }
            int tmp = dataArr[out];
            dataArr[out] = dataArr[min];
            dataArr[min] = tmp;
            System.out.println(Arrays.toString(dataArr));
        }
        System.out.println();
        System.out.println(Arrays.toString(dataArr));
    }
    /**
     * 冒泡排序
     */
    public void bubbleSort() {
        int[] dataArr = {1, 3, 5, 7, 9, 2, 4, 6, 8, 0};
        int out = 0;
        int in = 0;
        for (out = dataArr.length - 1; out >= 1; out--) {
            for (in = 0; in < out; in++) {
                if (dataArr[in] > dataArr[in + 1]) {
                    int tmp = dataArr[in];
                    dataArr[in] = dataArr[in + 1];
                    dataArr[in + 1] = tmp;
                }
            }
            System.out.println(Arrays.toString(dataArr));
        }
        System.out.println(Arrays.toString(dataArr));
    }
    /**
     * 测试入口
     * @param args
     */
    public static void main(String[] args) {
        new Sort().insertionSort();
    }
}
