package org.ybygjy.basic.basic.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by leye on 2017/9/30.
 */
public class ThreadCurrRandomTest {
    public static void main(String[] args) {
        System.out.println("distinctCount:" + ThreadLocalRandom.current().ints(1000).distinct().count());
        List<String> dataList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            dataList.add("A_" + i);
        }
        int i = 0;
        System.out.println(ThreadLocalRandom.current().ints(1000, 0, dataList.size()).distinct().count());
        ThreadLocalRandom.current().ints(1000, 0, dataList.size()).distinct().limit(40).forEach(s->{
            System.out.println(dataList.get(s));
        });
    }
}
