package org.ybygjy.jvm;

import java.util.List;
import java.util.Vector;

/**
 * 偏向锁
 * 偏向锁的核心思想是如果程序没有竞争则取消之前已经取得锁的线程同步操作，也就是说某一锁被线程获取后，进入偏向锁模式，当线程再进入请求这个锁时就无需要再进行相关的操作了
 * <p>-XX:-UseBiasedLocking -XX:BiasedLockingStartupDelay=0 -client</p>
 * Created by leye on 2016/12/26.
 */
public class BiasedLock {
    private static List<Integer> numberList = new Vector<Integer>();
    public static void main(String[] args) {
        long begin = System.currentTimeMillis();
        for (int i = 0; i < 100000000; i++) {
            numberList.add(i);
        }
        long end = System.currentTimeMillis() - begin;
        System.out.println("time cost : " + (end / 1000));
    }
}
