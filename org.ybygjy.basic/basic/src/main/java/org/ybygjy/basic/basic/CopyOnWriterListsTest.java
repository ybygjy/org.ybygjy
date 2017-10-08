package org.ybygjy.basic.basic;

import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Thread.sleep;

/**
 * Created by leye on 2017/9/30.
 */
public class CopyOnWriterListsTest {
    public static void main(String[] args) throws InterruptedException {
        List<String> dataList = new CopyOnWriteArrayList<>();
        dataList.add("A");
        dataList.add("A");
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (true) {
                    try {
                        dataList.add(String.valueOf(Math.random()));
                        dataList.remove(ThreadLocalRandom.current().nextInt(0, dataList.size()));
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        for (int i = 0; i < 15; i++) {
            new Thread(()->{
                while (true) {
                    try {
                        String str = dataList.toString();
                        String sss = str;
                        if (sss != null) {
                            continue;
                        }
                    } catch (ConcurrentModificationException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        Thread.currentThread().join(10000000);
    }
}
